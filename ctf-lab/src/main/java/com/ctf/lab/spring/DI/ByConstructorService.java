package com.ctf.lab.spring.DI;

import com.ctf.lab.spring.Bean;

/**
 * 通过构造器装配
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ByConstructorService {

    private Bean bean;

    public ByConstructorService(Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return bean;
    }
}
