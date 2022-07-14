package com.ctf.file.entity;

import java.io.Serializable;

import com.ctf.component.commons.entity.CommonEntity;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 文件信息的实体类
 *
 *
 */
public class File extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 8752570079717460672L;
	private Long id;// ID
	private String name;// 文件参数名
	private String originalFilename;// 文件名称
	private Long fileSize;// 文件大小，单位：字节
	private String contentType;// 数据类型
	private String fileType;// 文件类型
	private Short type;// 1:文件夹，2:文件
	private String url;// 下载URL
	private String content;// 文件字符串内容
	private String tenantCode;// 租户编码

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		File item = (File) o;
		return Objects.equal(id, item.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name).add("originalFilename", originalFilename).add("fileSize", fileSize)
				.add("contentType", contentType).add("fileType", fileType).add("type", type).add("url", url).add("content", content)
				.add("tenantCode", tenantCode).add("parentId", super.getParentId()).add("createTime", super.getCreateTime()).toString();
	}

}
