package com.ctf.lab.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * 事件A监听器
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class EventListener implements ApplicationListener<EventA> {
    @Override
    public void onApplicationEvent(EventA event) {
        System.out.println("接收到事件: "+event.getSource());
        System.out.println("处理事件");
    }
}
