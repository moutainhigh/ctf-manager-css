package com.ctf.sms.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ctf.common.result.Result;
import com.ctf.sms.pojo.entity.SmsAdvert;
import com.ctf.sms.service.SmsAdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "「移动端」营销广告")
@RestController
@RequestMapping("/app-api/v1/adverts")
@Slf4j
@AllArgsConstructor
public class AdvertController {

    private SmsAdvertService smsAdvertService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public Result list() {
        LambdaQueryWrapper<SmsAdvert> queryWrapper = new LambdaQueryWrapper<SmsAdvert>()
                .orderByAsc(SmsAdvert::getSort);
        List<SmsAdvert> data = smsAdvertService.list(queryWrapper);
        return Result.success(data);
    }
}
