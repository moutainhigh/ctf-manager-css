package com.ctf.component.commons.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.ctf.component.commons.validator.InsertValidator;
import com.ctf.component.commons.validator.UpdateValidator;

/**
 * 实体类共用字段
 *
 *
 */
public class CommonEntity extends TimeEntity implements Serializable {

	private static final long serialVersionUID = 6272491356994391729L;
	@NotNull(groups = { InsertValidator.class, UpdateValidator.class })
	private Long parentId;// 上级ID

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
