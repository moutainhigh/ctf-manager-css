package com.zdf.ocean.feign;

import org.springframework.cloud.openfeign.FeignClient;
import com.zdf.common.AppConstant;

/**
 * 数据存储服务Feign 示例代码
 *
 * @Author liwei
 * @Date 2022年7月11日14:37:01
 * @Version 1.0
 */
@FeignClient(value = AppConstant.APPLICATION_ocean_NAME, fallback = com.zdf.ocean.feign.DataStorageFeignFallback.class)
public interface IDataStorageFeign {


}
