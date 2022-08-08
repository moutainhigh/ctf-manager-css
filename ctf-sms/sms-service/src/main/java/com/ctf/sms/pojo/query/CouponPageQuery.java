package com.ctf.sms.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinyi
 * @desc: 优惠券分页查询对象
 * @date 2022/8/5 10:30
 */
@ApiModel("优惠券分页查询对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponPageQuery extends BasePageQuery {

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("优惠券码")
    private String code;
}
