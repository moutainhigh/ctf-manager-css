package com.ctf.lab.spring.bean.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * 获取当前环境
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */

public class IEnvironmentAware implements EnvironmentAware {

    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        System.out.println("\n--------------------");
        System.out.println("当前环境:"+environment);
        System.out.println("--------------------");
    }
}
