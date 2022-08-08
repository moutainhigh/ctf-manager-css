package com.ctf.sms.converter;


import com.ctf.sms.pojo.entity.SmsCoupon;
import com.ctf.sms.pojo.form.CouponForm;
import com.ctf.sms.pojo.vo.CouponPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 优惠券对象转换器
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Mapper(componentModel = "spring")
public interface SmsCouponConverter {

    @Mappings({
            @Mapping(target = "platformLabel", expression = "java(com.ctf.common.base.IBaseEnum.getLabelByValue(entity.getPlatform(), com.ctf.sms.common.enums.PlatformEnum.class))"),
            @Mapping(target = "typeLabel", expression = "java(com.ctf.common.base.IBaseEnum.getLabelByValue(entity.getType(), com.ctf.sms.common.enums.CouponTypeEnum.class))"),
            @Mapping(target = "faceValueLabel", expression = "java(com.ctf.sms.common.utils.CouponUtils.getFaceValue(entity.getType(),entity.getFaceValue(),entity.getDiscount()))"),
            @Mapping(
                    target = "validityPeriodLabel",
                    expression = "java(com.ctf.sms.common.utils.CouponUtils.getValidityPeriod(entity.getValidityPeriodType(),entity.getValidityDays(),entity.getValidityBeginTime(),entity.getValidityBeginTime()))"
            ),
            @Mapping(target = "minPointLabel", expression = "java(cn.hutool.core.util.NumberUtil.toStr(cn.hutool.core.util.NumberUtil.div(entity.getMinPoint(),new java.math.BigDecimal(100)).setScale(2)))"),
    })
    CouponPageVO entity2PageVO(SmsCoupon entity);


    List<CouponPageVO> entity2PageVO(List<SmsCoupon> entities);


    @Mappings({
            @Mapping(target = "discount",expression = "java(cn.hutool.core.util.NumberUtil.div(form.getDiscount(),10L))"),
    })
    SmsCoupon form2Entity(CouponForm form);


    @Mappings({
            @Mapping(target = "discount",expression = "java(cn.hutool.core.util.NumberUtil.mul(entity.getDiscount(),10L))"),
    })
    CouponForm entity2Form(SmsCoupon entity);
}
