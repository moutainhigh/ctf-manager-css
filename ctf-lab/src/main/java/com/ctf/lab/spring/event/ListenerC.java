package com.ctf.lab.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ListenerC {

    static class Event{

    }

    @EventListener
    @Order(1)
    public void listhen1(EventC eventC){
        System.out.println("接收到事件1:"+eventC);
        System.out.println("处理事件");
    }

    @EventListener
    @Order(2)
    public void listhen2(EventC eventc){
        System.out.println("接收到事件2:"+eventc);
        System.out.println("处理事件");
    }
}
