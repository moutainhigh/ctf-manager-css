package com.ctf.css.pojo.vo.ex;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangyizheng
 * @Date 2022/8/23 09:03
 * @Describe TourSchemeVO
 */
@Data
public class TourSchemeVO {

    /**
     * id
     */
    private Long id;

    /**
     * 方案编号
     */
    private String schemeCode;

    /**
     * 领域名称
     */
    private String superviseDomainName;

    /**
     * 巡检方案
     */
    private String schemeName;

    /**
     * 状态 注：
     * 0 收集中：方案已经被督导使用并已经巡店（即签到但未提交，未签名）
     * 1 未开始：已创建方案，未指定督导也未指定门店；
     *  已经做巡检计划，还未开始巡店的；方案被督导签名提交被使用过；
     * 2 已停止
     */
    private String status;

    /**
     * 题目数量
     */
    private Integer schemeTopicNum;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
