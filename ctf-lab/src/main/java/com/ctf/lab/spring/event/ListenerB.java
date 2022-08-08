package com.ctf.lab.spring.event;

import org.springframework.context.event.EventListener;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ListenerB {

    @EventListener
    public void listen(EventB eventB){
        System.out.println("接收到事件:"+eventB);
        System.out.println("处理事件");
    }
}
