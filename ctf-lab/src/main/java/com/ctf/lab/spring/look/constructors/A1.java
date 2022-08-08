package com.ctf.lab.spring.look.constructors;

/**
 * 构造器注入的bean
 * @author H.m
 * @date 2022/8/5 10:30
 */
//@Component
public class A1 {

    private B1 b1;

    public A1(B1 b1) {
        this.b1 = b1;
    }
}
