package com.ctf.lab.spring.look.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Component
public class A2 {

    private String username = "a";

    private B2 b2;

    public A2() {
        System.out.println("实例化A2:"+this);
    }
    @Autowired
    public void setB(B2 b2) {
        System.out.println("注入属性B2:"+b2);
        this.b2 = b2;
    }


}
