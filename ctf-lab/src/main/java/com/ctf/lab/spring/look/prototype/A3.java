package com.ctf.lab.spring.look.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Component
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class A3  {

    private String username = "a";

    private B3 b3;

    public A3() {
        System.out.println("实例化A2:"+this);
    }
    @Autowired
    public void setB(B3 b3) {
        System.out.println("注入属性B2:"+ b3);
        this.b3 = b3;
    }



}
