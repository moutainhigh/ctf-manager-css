package com.ctf.sms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 优惠券分页视图对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@ApiModel("优惠券分页视图对象")
@Data
public class CouponPageVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("优惠券名称")
    private String name;

    @ApiModelProperty("优惠券码")
    private String code;

    @ApiModelProperty("使用平台")
    private String platformLabel;

    @ApiModelProperty("优惠券类型标签")
    private String typeLabel;

    @ApiModelProperty("优惠券面值")
    private String faceValueLabel;

    @ApiModelProperty("使用门槛")
    private String minPointLabel;

    @ApiModelProperty("优惠券有效期")
    private String validityPeriodLabel;

    @ApiModelProperty("使用说明")
    private String remark;

}
