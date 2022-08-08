package com.ctf.lab.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件A
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class EventA extends ApplicationEvent {
    public EventA(Object source) {
        super(source);
    }
}
