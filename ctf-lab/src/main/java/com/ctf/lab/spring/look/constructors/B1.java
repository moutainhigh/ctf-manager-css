package com.ctf.lab.spring.look.constructors;

/**
 * 构造器注入的bean
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
//@Component
public class B1 {

    private A1 a1;

    public B1(A1 a1) {
        this.a1 = a1;
    }
}
