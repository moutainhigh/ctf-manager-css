package com.ctf.lab.spring.beanDefinition;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class BeanC  {


    public void destroy(){
        System.out.println("生命周期回调方法，在bean被销毁时调用");
    }


    public void afterPropertiesSet() {
        System.out.println("生命周期回调方法，在bean完成属性注入后调用");
    }
}
