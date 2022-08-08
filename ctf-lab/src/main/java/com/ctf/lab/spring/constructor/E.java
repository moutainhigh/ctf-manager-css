package com.ctf.lab.spring.constructor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 多个@Autowired标注的构造器
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class E {

    private String name;
    private A a;

    @Autowired(required = false)
    public E(String name, A a) {
        this.name = name;
        this.a = a;
        System.out.println("一个参数A和参数name");
    }

    @Autowired(required = false)
    public E(A a) {
        this.a = a;
        System.out.println("一个参数A构造器");
    }

    public E() {
        System.out.println("无参");
    }
}
