package com.ctf.sms.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 广告分页列表查询对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@ApiModel("广告分页查询对象")
@Data
public class AdvertPageQuery extends BasePageQuery {

    @ApiModelProperty("关键字")
    private String keywords;

}
