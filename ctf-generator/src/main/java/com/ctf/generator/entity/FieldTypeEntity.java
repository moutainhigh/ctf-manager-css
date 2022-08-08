package com.ctf.generator.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 字段类型管理
 *
 * @author H.m
 */
@Data
@TableName("gen_field_type")
public class FieldTypeEntity {
	/**
	 * id
	 */
	@TableId(type= IdType.AUTO)
	private Long id;
    /**
     * 字段类型
     */
	private String columnType;
    /**
     * 属性类型
     */
	private String attrType;
	/**
	 * 属性包名
	 */
	private String packageName;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
}
