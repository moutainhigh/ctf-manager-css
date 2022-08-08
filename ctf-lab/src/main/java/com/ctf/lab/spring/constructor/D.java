package com.ctf.lab.spring.constructor;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class D {

    public static D create(){
        System.out.println("factory");
        return new D();
    }

}
