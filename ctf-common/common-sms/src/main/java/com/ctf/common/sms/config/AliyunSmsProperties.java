package com.ctf.common.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云短信配置
 * <p>
 * 配置文件 ctf-common.yml
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@ConfigurationProperties(prefix = "aliyun.sms")
@Configuration
@Data
public class AliyunSmsProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private String domain;

    private String regionId;

    private String templateCode;

    private String signName;

}
