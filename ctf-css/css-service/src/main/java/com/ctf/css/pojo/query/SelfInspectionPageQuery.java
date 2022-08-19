package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/18 15:19
 * @Describe InspectionPageQuery
 */
@ApiModel("任务下发-自检分页查询对象")
@Data
public class SelfInspectionPageQuery extends BasePageQuery {

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
     * 状态
     */
    private String status;
}
