package com.ctf.admin.api;

import com.ctf.admin.api.fallback.UserFeignFallbackClient;
import com.ctf.admin.dto.UserAuthDTO;
import com.ctf.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName UserFeignClient
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@FeignClient(value = "ctf-admin", fallback = UserFeignFallbackClient.class)
public interface UserFeignClient {

    @GetMapping("/api/v1/users/username/{username}")
    Result<UserAuthDTO> getUserByUsername(@PathVariable String username);
}
