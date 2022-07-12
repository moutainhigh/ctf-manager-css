package com.zdf.ocean.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 *  Mapper 示例代码
 * @author liwei
 * @since 2022-07-11
 */
@Component
@FeignClient(name = "jayud-fms-air")
public interface AuthClient {
}
