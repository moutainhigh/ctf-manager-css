package com.ctf.lab.spring.beanDefinition;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class BeanD {

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                '}';
    }

    public BeanD() {
    }

    public BeanD(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
