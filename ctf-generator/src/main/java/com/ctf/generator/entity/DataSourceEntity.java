package com.ctf.generator.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 数据源管理
 *
 * @author H.m
 */
@Data
@TableName("gen_datasource")
public class DataSourceEntity {
	/**
	 * id
	 */
	@TableId(type= IdType.AUTO)
	private Long id;
    /**
     * 数据库类型 MySQL、Oracle、PostgreSQL
     */
	private String dbType;
    /**
     * 连接名
     */
	private String connName;
    /**
     * URL
     */
	private String connUrl;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
}
