package com.ctf.common.rabbitmq.dynamic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author H.m
 * @date 2022/8/5 10:30
 */
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitModuleProperties {

    private List<RabbitModuleInfo> modules;

}
