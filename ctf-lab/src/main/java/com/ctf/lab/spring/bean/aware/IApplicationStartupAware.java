package com.ctf.lab.spring.bean.aware;

import org.springframework.context.ApplicationStartupAware;
import org.springframework.core.metrics.ApplicationStartup;

/**
 *  获取ApplicationStartup
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IApplicationStartupAware implements ApplicationStartupAware {

    private ApplicationStartup applicationStartup;
    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        this.applicationStartup = applicationStartup;
        System.out.println("\n--------------------");
        System.out.println("ApplicationStartupAware:"+applicationStartup);
        System.out.println("--------------------");
    }
}
