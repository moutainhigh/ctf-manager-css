package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/21 11:39
 * @Describe RestultPageQuery
 */
@ApiModel("督导结果-分页查询条件对象")
@Data
public class RestultPageQuery extends BasePageQuery {

    /**
     * 用户工号
     */
    private String staffCode;

    /**
     * 用户名
     */
    private String staffName;

    /**
     * 品牌名称
     */
    private String bandName;

    /**
     * 店铺类型(dict_item)
     */
    private String storeType;

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
     * 巡检类型 0独立巡检 1联合巡检
     */
    private Integer inspectionType;

    /**
     * 领域名称
     */
    private String superviseDomainName;
}
