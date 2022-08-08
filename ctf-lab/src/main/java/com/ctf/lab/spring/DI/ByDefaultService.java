package com.ctf.lab.spring.DI;

import com.ctf.lab.spring.Bean;

/**
 *
 * 演示默认装配（no）
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ByDefaultService {

    public Bean bean;

    @Override
    public String toString() {
        return "ByDefaultService{" +
                "bean=" + bean +
                '}';
    }


}
