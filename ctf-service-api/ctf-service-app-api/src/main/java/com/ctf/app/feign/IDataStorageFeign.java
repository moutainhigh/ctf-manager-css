package com.ctf.air.feign;

import com.ctf.common.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 数据存储服务Feign
 *
 * @Author cyc
 * @Date 2022/6/13 20:31
 * @Version 1.0
 */
@FeignClient(value = AppConstant.APPLICATION_AIR_NAME, fallback = DataStorageFeignFallback.class)
public interface IDataStorageFeign {

}
