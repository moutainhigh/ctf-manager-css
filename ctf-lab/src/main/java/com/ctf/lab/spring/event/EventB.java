package com.ctf.lab.spring.event;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class EventB {
    String name;

    public EventB(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventB{" +
                "name='" + name + '\'' +
                '}';
    }
}
