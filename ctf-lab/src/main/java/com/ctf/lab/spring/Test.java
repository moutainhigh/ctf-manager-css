package com.ctf.lab.spring;

/**
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface Test {

    default void test(){
        System.out.println(this);
    };
}
