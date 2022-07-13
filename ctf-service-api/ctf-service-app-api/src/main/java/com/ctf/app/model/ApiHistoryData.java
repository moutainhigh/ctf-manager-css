package com.ctf.air.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 示例代码
 *
 * @Author liwei
 * @Date 2022年7月11日16:43:35
 * @Version 1.0
 */
@TableName("api_history_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiHistoryData {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 业务类型
	 */
	private String bizType;

	/**
	 * 模块类型
	 */
	private String moduleType;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * objectId
	 */
	private String objectId;

	/**
	 * 承运商ID
	 */
	private String supplierId;

	/**
	 * 承运商appId
	 */
	private String appId;

	/**
	 * 保存时间
	 */
	private Date saveTime;
}

