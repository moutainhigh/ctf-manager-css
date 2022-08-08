package com.ctf.admin.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 权限分页查询对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
@ApiModel
public class PermPageQuery extends BasePageQuery {

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("菜单ID")
    private Long menuId;

}
