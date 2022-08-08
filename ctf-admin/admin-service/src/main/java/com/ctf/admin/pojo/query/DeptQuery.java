package com.ctf.admin.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门分页查询对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@ApiModel("部门分页查询对象")
@Data
public class DeptQuery {

    @ApiModelProperty("关键字(部门名称)")
    private String keywords;

    @ApiModelProperty("状态(1->正常；0->禁用)")
    private Integer status;

}
