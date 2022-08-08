package com.ctf.sms.api.app;

import com.ctf.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author H.m
 * @desc 优惠券领券记录APP端Feign接口

 * @date 2022/8/5 10:30
 */
@FeignClient(value = "ctf-sms")
public interface CouponRecordFeignClient {

    @GetMapping("/api.app/v1/coupon_record/list")
    Result list();

    @GetMapping("/api.app/v1/coupon_record/{id}/detail")
    Result detail(@PathVariable("id") String id);

    @PostMapping("/api.app/v1/coupon_record")
    Result add(@RequestParam("couponId") String couponId);

    @PostMapping("/api.app/v1/coupon_record/push")
    Result add(@RequestParam("couponType") Integer couponType,
               @RequestParam("userId") Long userId);

}
