package com.ctf.lab.spring;

import com.ctf.lab.spring.DI.ConstructorService;
import com.ctf.lab.spring.DI.SetterService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试两种注入方式(构造器和setter)
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class DITypeTests {


    @Test
    void setter(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean(Bean.class);
        applicationContext.registerBean(SetterService.class);
        applicationContext.refresh();
        SetterService setterService = applicationContext.getBean(SetterService.class);
        setterService.test();
    }

    @Test
    void constructor(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean(Bean.class);
        applicationContext.registerBean(ConstructorService.class);
        applicationContext.refresh();
        ConstructorService constructorService = applicationContext.getBean(ConstructorService.class);
        constructorService.test();
    }
}
