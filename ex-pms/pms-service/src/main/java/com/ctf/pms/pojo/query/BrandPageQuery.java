package com.ctf.pms.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 品牌分页列表查询对象
 *
 * @author H.m
 * @date 2021/7/11
 */
@ApiModel("品牌分页查询对象")
@Data
public class BrandPageQuery extends BasePageQuery {

    @ApiModelProperty("关键字")
    private String keywords;

}
