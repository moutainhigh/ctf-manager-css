package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/19 11:39
 * @Describe InspectionPageQuery
 */
@ApiModel("任务下发-巡检分页查询对象")
@Data
public class InspectionPageQuery extends BasePageQuery {

    /**
     * 关键字；门店编号或名称
     */
    private String keywords;

    /**
     * 状态
     */
    private String status;
}
