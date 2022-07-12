package com.zdf.ocean;

import com.zdf.common.filter.UserHeaderFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "com.zdf")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zdf")
@MapperScan(basePackages = {"com.zdf.**.mapper"})
@EnableWebSecurity
@ComponentScan(basePackages = {"com.zdf"},excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserHeaderFilter.class))
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JayudOceanApplication {

    public static void main(String[] args) {
        SpringApplication.run(JayudOceanApplication.class, args);
    }

}
