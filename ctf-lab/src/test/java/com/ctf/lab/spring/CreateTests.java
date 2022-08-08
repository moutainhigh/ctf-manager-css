package com.ctf.lab.spring;


import com.ctf.lab.spring.create.ConstructorService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * 三种实例化bean方式
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class CreateTests {

    @Test
    void constructor(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConstructorService.class);
    }

    @Test
    void factoryMethod(){
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/create/FactoryClass.xml");
    }

    @Test
    void factoryBean(){
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/create/FactoryBean.xml");
    }
}
