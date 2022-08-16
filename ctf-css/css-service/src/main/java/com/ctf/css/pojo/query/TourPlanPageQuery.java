package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/15 14:48
 * @Describe TourPlanQuery
 */
@ApiModel("巡检计划-门店分页查询对象")
@Data
public class TourPlanPageQuery extends BasePageQuery {
    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 品牌名称
     */
    private String bandName;

    /**
     * 区域
     */
    private String area;

    /**
     * 大区
     */
    private String bigArea;

    /**
     * 小区
     */
    private String smallArea;

    /**
     * 状态
     */
    private String status;
}
