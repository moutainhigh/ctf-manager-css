package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/22 19:39
 * @Describe TourSchemePageQuery
 */
@ApiModel("数据收集分页查询条件对象")
@Data
public class TourSchemePageQuery extends BasePageQuery {
    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 状态
     */
    private String status;
}
