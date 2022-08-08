package com.ctf.lab.spring.DI;

import com.ctf.lab.spring.Bean;

/**
 *
 *通过属性名称装配，需要提供set方法，且符合命名规范
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ByNameService {


    public Bean bean;

    @Override
    public String toString() {
        return "ByDefaultService{" +
                "bean=" + bean +
                '}';
    }
    //需要提供set方法，且命名为setXxx
    public void setBean(Bean bean) {
        this.bean = bean;
    }
}
