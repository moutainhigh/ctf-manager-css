package com.ctf.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ctf.account.client.SysUserDetailServiceClient;
import com.ctf.admin.client.SysDictServiceClient;
import com.ctf.admin.client.SysRoleServiceClient;
import com.ctf.component.commons.result.PaginationBuilder;
import com.ctf.component.commons.utils.CollectionUtils;
import com.ctf.component.commons.utils.CurrentUserUtils;
import com.ctf.component.commons.utils.SequenceGenerator;
import com.ctf.file.mapper.FileMapper;
import com.ctf.file.service.FileService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 文件信息的业务逻辑实现层
 *
 *
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static SequenceGenerator sequenceGenerator = new SequenceGenerator();

	@Autowired
	private SysUserDetailServiceClient sysUserDetailServiceClient;
	@Autowired
	private SysRoleServiceClient sysRoleServiceClient;
	@Autowired
	private SysDictServiceClient sysDictServiceClient;
	@Autowired
	private FileMapper fileMapper;

	/**
	 * 查询文件分页
	 */
	@Override
	public Map<String, Object> queryFile(Integer currentPage, Integer pageSize, Long id, String originalFilename, String content, Long parentId,
			Long previousId, String sorter) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("originalFilename", originalFilename);
		paramMap.put("content", content);
		paramMap.put("tenantCode", CurrentUserUtils.getOAuth2AuthenticationDetailsInfo().get("tenantCode"));// 当前用户的租户编码
		if (StringUtils.isNotBlank(sorter)) {
			String sort = sorter.substring(0, sorter.lastIndexOf('_'));
			String sequence = "ascend".equals(sorter.substring(sorter.lastIndexOf('_') + 1)) ? "ASC" : "DESC";
			paramMap.put("sort", sort);
			paramMap.put("sequence", sequence);
		} else {
			paramMap.put("sort", "createTime");
			paramMap.put("sequence", "DESC");
		}
		paramMap.put("id", id);
		paramMap.put("parentId", parentId);
		paramMap.put("previousId", previousId);
		Page<Object> page = PageHelper.startPage(currentPage, pageSize);
		List<LinkedHashMap<String, Object>> resultList = fileMapper.queryFile(paramMap);
		logger.info("此文件组已查询： {}", originalFilename);

		String roleData = sysRoleServiceClient.queryRoleData("uploaddownload", CurrentUserUtils.getOAuth2AuthenticationDetailsInfo().get("name"));
		String[] roleDataArray = roleData == null ? null : roleData.split(",");
		if (roleDataArray != null && roleDataArray.length > 0) {// 处理数据权限
			return PaginationBuilder.buildResult(CollectionUtils.convertFilterList(resultList, roleDataArray), page.getTotal(), currentPage, pageSize);
		} else {
			return PaginationBuilder.buildResult(resultList, page.getTotal(), currentPage, pageSize);
		}
	}

	/**
	 * 查询文件类型名称
	 */
	@Override
	public List<LinkedHashMap<String, Object>> queryFileTypeName() {
		return sysDictServiceClient.queryDictByDictType("file");
	}

	/**
	 * 删除文件
	 */
	@Override
	public void deleteFile(Long[] id) throws IOException {
		List<String> urlList = fileMapper.queryUrlList(id);
		File rootDirectoryPath = new File(ResourceUtils.getURL("classpath:").getPath());
		for (int i = 0; i < urlList.size(); i++) {
			String path = rootDirectoryPath + urlList.get(i);
			Files.deleteIfExists(Paths.get(path));// 删除磁盘里的文件
		}

		Set<Long> ids = new HashSet<>();
		for (int i = 0; i < id.length; i++) {
			ids.add(id[i]);
			getRecursiveIds(id[i], ids);
		}
		Long[] fileId = ids.stream().toArray(Long[]::new);
		fileMapper.deleteFile(fileId); // 删除数据库文件记录

		logger.info("此文件组已彻底删除，ID数组： {}", id.toString());
	}

	/**
	 * 上传文件
	 */
	@Override
	public void uploadFile(MultipartFile file, Long id, Long parentId, String uploadType, String fileType) throws Exception {
		String fileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'), file.getOriginalFilename().length());
		String path = generatePath(fileName);

		Files.write(Paths.get(path).normalize().toAbsolutePath(), file.getBytes(), StandardOpenOption.CREATE_NEW);

		if ("avatar".equals(uploadType)) {
			sysUserDetailServiceClient.updateAvatar("/api/file/static/upload/" + fileName, id);
		}

		com.ctf.file.entity.File entity = new com.ctf.file.entity.File();
		entity.setName(file.getName());
		entity.setOriginalFilename(file.getOriginalFilename());
		entity.setFileSize(file.getSize());
		entity.setContentType(file.getContentType());
		entity.setUrl("/static/upload/" + fileName);
		entity.setFileType(fileType);
		entity.setType((short) 2);

		if (file.getOriginalFilename().toLowerCase().indexOf(".txt") != -1) {
			entity.setContent(new String(file.getBytes()));
		} else if (file.getOriginalFilename().toLowerCase().indexOf(".pdf") != -1) {
			FileInputStream fileInputStream = new FileInputStream(path);
			RandomAccessRead randomAccessRead = new RandomAccessBufferedFileInputStream(fileInputStream);
			PDFParser pdfParser = new PDFParser(randomAccessRead);
			pdfParser.parse();
			PDDocument pdDocument = pdfParser.getPDDocument();
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			entity.setContent(pdfTextStripper.getText(pdDocument));
			pdDocument.close();
			randomAccessRead.close();
			fileInputStream.close();
		} else if (file.getOriginalFilename().toLowerCase().indexOf(".xls") != -1 || file.getOriginalFilename().toLowerCase().indexOf(".xlsx") != -1) {
			File xlsFile = new File(path);
			Workbook workbook = WorkbookFactory.create(xlsFile);
			int numberOfSheets = workbook.getNumberOfSheets();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				int rowNumbers = sheet.getLastRowNum() + 1;
				Row temp = sheet.getRow(0);
				if (temp == null) {
					continue;
				}
				int cells = temp.getPhysicalNumberOfCells();
				for (int row = 0; row < rowNumbers; row++) {
					Row r = sheet.getRow(row);
					for (int col = 0; col < cells; col++) {
						if (r != null) {
							stringBuilder.append(r.getCell(col) == null ? "" : r.getCell(col));
						}
					}
				}
			}
			workbook.close();
			entity.setContent(stringBuilder.toString());
		} else if (file.getOriginalFilename().toLowerCase().endsWith(".doc")) {
			FileInputStream fileInputStream = new FileInputStream(path);
			WordExtractor wordExtractor = new WordExtractor(fileInputStream);
			entity.setContent(wordExtractor.getText());
			wordExtractor.close();
			fileInputStream.close();
		} else if (file.getOriginalFilename().toLowerCase().endsWith(".docx")) {
			OPCPackage opcPackage = POIXMLDocument.openPackage(path);
			POIXMLTextExtractor poiXMLTextExtractor = new XWPFWordExtractor(opcPackage);
			entity.setContent(poiXMLTextExtractor.getText());
			opcPackage.close();
			poiXMLTextExtractor.close();
		}

		entity.setId(sequenceGenerator.nextId());
		entity.setParentId(parentId);
		entity.setTenantCode(CurrentUserUtils.getOAuth2AuthenticationDetailsInfo().get("tenantCode"));// 当前用户的租户编码
		fileMapper.insertFileSysUser(sequenceGenerator.nextId(), entity.getId(), id);
		fileMapper.insertFile(entity);

		logger.info("此文件已上传： {}", file.getOriginalFilename());
	}

	/**
	 * 上传文件到与jar包同级的外部目录
	 *
	 * @param fileName 已修改成时间戳的文件名称
	 * @return
	 * @throws IOException
	 */
	private String generatePath(String fileName) throws IOException {
		File rootDirectoryPath = new File(ResourceUtils.getURL("classpath:").getPath());
		if (!rootDirectoryPath.exists()) {
			rootDirectoryPath = new File("");
		}
		File uploadPath = new File(rootDirectoryPath.getAbsolutePath(), "static/upload/");
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		return rootDirectoryPath.getAbsolutePath() + "/static/upload/" + fileName;
	}

	/**
	 * 新增文件夹
	 */
	@Override
	public void addFolder(Long id, String originalFilename, Long parentId) {
		com.ctf.file.entity.File entity = new com.ctf.file.entity.File();
		entity.setOriginalFilename(originalFilename);
		entity.setType((short) 1);
		entity.setId(sequenceGenerator.nextId());
		entity.setParentId(parentId);
		entity.setTenantCode(CurrentUserUtils.getOAuth2AuthenticationDetailsInfo().get("tenantCode"));// 当前用户的租户编码
		fileMapper.insertFileSysUser(sequenceGenerator.nextId(), entity.getId(), id);
		fileMapper.insertFile(entity);

		logger.info("此文件夹已新增： {}", originalFilename);
	}

	/**
	 * 使用递归的方式查询所有子节点的id
	 *
	 * @param id  子节点id
	 * @param ids 子节点id集
	 */
	private void getRecursiveIds(Long id, Set<Long> ids) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentId", id);
		List<LinkedHashMap<String, Object>> list = fileMapper.queryFile(paramMap);
		for (int i = 0; i < list.size(); i++) {
			for (Entry<String, Object> entry : list.get(i).entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (key.equals("id")) {
					ids.add(Long.valueOf(String.valueOf(value)));
					getRecursiveIds(Long.valueOf(String.valueOf(value)), ids);
				}
			}
		}
	}

}
