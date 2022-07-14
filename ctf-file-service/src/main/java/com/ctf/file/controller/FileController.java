package com.ctf.file.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ctf.component.commons.result.ActionResult;
import com.ctf.component.commons.result.ListResult;
import com.ctf.component.commons.result.ResultBuilder;
import com.ctf.component.commons.utils.FileUtils;
import com.ctf.file.service.FileService;
import com.ctf.file.vo.FileVO;

/**
 * 文件信息的控制层
 *
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FileService fileService;

	/**
	 * 查询文件分页
	 *
	 * @param fileVO 文件前端参数
	 * @return
	 */
	@GetMapping(path = "/queryFile")
	public ListResult<Object> queryFile(FileVO fileVO) {
		Map<String, Object> data = fileService.queryFile(fileVO.getCurrentPage(), fileVO.getPageSize(), fileVO.getId(), fileVO.getOriginalFilename(),
				fileVO.getContent(), fileVO.getParentId(), fileVO.getPreviousId(), fileVO.getSorter());
		return ResultBuilder.buildListSuccess(data);
	}

	/**
	 * 查询文件类型名称
	 *
	 * @return
	 */
	@GetMapping(path = "/queryFileTypeName")
	public ListResult<Object> queryFileTypeName() {
		List<LinkedHashMap<String, Object>> data = fileService.queryFileTypeName();
		return ResultBuilder.buildListSuccess(data);
	}

	/**
	 * 删除文件
	 *
	 * @param id 文件ID
	 * @return
	 * @throws IOException
	 */
	@PostMapping(path = "/deleteFile")
	public ActionResult deleteFile(@RequestParam(name = "id", required = true) Long[] id) throws IOException {
		fileService.deleteFile(id);
		return ResultBuilder.buildActionSuccess();
	}

	/**
	 * 新增文件夹
	 *
	 * @param id               用户ID
	 * @param originalFilename 文件夹名称
	 * @param parentId         上级ID
	 * @return
	 */
	@PostMapping(path = "/addFolder")
	public ActionResult addFolder(@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "originalFilename", required = true) String originalFilename,
			@RequestParam(name = "parentId", required = true) Long parentId) {
		fileService.addFolder(id, originalFilename, parentId);
		return ResultBuilder.buildActionSuccess();
	}

	/**
	 * 上传文件
	 *
	 * @param file       文件资源
	 * @param id         用户ID
	 * @param parentId   上级ID
	 * @param uploadType 上传类型
	 * @param fileType   文件类型
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/uploadFile", consumes = { "multipart/form-data" })
	public ActionResult uploadFile(@RequestParam(name = "file", required = true) MultipartFile file, @RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "parentId", required = false) Long parentId, @RequestParam(name = "uploadType", required = false) String uploadType,
			@RequestParam(name = "fileType", required = false) String fileType) throws Exception {
		fileService.uploadFile(file, id, parentId, uploadType, fileType);
		return ResultBuilder.buildActionSuccess();
	}

	/**
	 * 下载文件
	 *
	 * @param request          请求对象
	 * @param response         响应对象
	 * @param originalFilename 文件名称
	 * @param url              下载URL
	 */
	@PostMapping(path = "/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "originalFilename", required = true) String originalFilename, @RequestParam(name = "url", required = true) String url) {
		try {
			FileUtils.downloadFile(originalFilename, url, response);
		} catch (Exception e) {
			logger.warn(e.toString());
		}
	}

}
