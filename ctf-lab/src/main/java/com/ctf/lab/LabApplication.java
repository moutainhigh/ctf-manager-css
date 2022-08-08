package com.ctf.lab;

import com.ctf.oms.api.OrderFeignClient;
import com.ctf.pms.api.SkuFeignClient;
import com.ctf.ums.api.MemberFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 实验室启动类
 *
 * @author H.m
 * * @date 2022/8/5 10:30
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {OrderFeignClient.class, SkuFeignClient.class, MemberFeignClient.class})
public class LabApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }
}
