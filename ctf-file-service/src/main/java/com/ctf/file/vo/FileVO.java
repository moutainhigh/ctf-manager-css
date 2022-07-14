package com.ctf.file.vo;

import java.io.Serializable;

import com.ctf.component.commons.vo.CommonVO;

/**
 * 文件信息的参数类
 *
 *
 */
public class FileVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -3144811184148006684L;
	private Long id;// 文件ID
	private Long parentId;// 上级ID
	private Long previousId;// 返回上一级ID
	private String originalFilename;// 文件名称
	private String content;// 文件字符串内容

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getPreviousId() {
		return previousId;
	}

	public void setPreviousId(Long previousId) {
		this.previousId = previousId;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
