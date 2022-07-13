package com.ctf.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class FrontEndVo {

    @ApiModelProperty("label")
    private String label;

    @ApiModelProperty("prop")
    private String prop;

    //列表字段简称
    @ApiModelProperty("abbreviation")
    private String abbreviation;

    @ApiModelProperty("是否默认隐藏(true隐藏)")
    private boolean hidden = false;

}
