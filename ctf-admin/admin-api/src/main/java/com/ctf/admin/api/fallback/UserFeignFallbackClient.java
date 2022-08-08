package com.ctf.admin.api.fallback;

import com.ctf.admin.api.UserFeignClient;
import com.ctf.common.result.Result;
import com.ctf.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserFeignFallbackClient
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Component
@Slf4j
public class UserFeignFallbackClient implements UserFeignClient {

    @Override
    public Result getUserByUsername(String username) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return Result.failed(ResultCode.DEGRADATION);
    }
}
