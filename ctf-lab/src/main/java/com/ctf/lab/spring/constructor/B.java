package com.ctf.lab.spring.constructor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class B {

    private A a;
    public B() {
        System.out.println("B无参构造函数");
    }

    @Autowired
    public B(A a) {
        System.out.println("autowired指定的构造函数");
        this.a = a;
    }
}
