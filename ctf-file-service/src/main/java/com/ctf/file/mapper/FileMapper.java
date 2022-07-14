package com.ctf.file.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ctf.file.entity.File;

/**
 * 文件信息的数据持久接口层
 * 
 * 
 */
public interface FileMapper {

	/**
	 * 查询文件分页
	 * 
	 * @param paramMap 参数Map
	 * @return
	 */
	List<LinkedHashMap<String, Object>> queryFile(Map<String, Object> paramMap);

	/**
	 * 新增文件记录
	 * 
	 * @param file 文件对象
	 * @return
	 */
	int insertFile(File file);

	/**
	 * 新增文件与系统用户关联记录
	 * 
	 * @param id        文件与系统用户关联ID
	 * @param fileId    文件ID
	 * @param sysUserId 系统用户ID
	 * @return
	 */
	int insertFileSysUser(Long id, Long fileId, Long sysUserId);

	/**
	 * 删除文件
	 * 
	 * @param id 文件ID
	 * @return
	 */
	int deleteFile(Long[] id);

	/**
	 * 查询下载URL列表
	 * 
	 * @param id 文件ID
	 * @return
	 */
	List<String> queryUrlList(Long[] id);

}
