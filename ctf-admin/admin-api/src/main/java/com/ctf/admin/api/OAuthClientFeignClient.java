package com.ctf.admin.api;

import com.ctf.admin.dto.ClientAuthDTO;
import com.ctf.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OAuthClientFeignClient
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@FeignClient(value = "ctf-admin", contextId = "oauth-client")
public interface OAuthClientFeignClient {

    @GetMapping("/api/v1/oauth-clients/getOAuth2ClientById")
    Result<ClientAuthDTO> getOAuth2ClientById(@RequestParam String clientId);
}
