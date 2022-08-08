package com.ctf.lab.spring.DI;

import com.ctf.lab.spring.Bean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 演示通过set方法注入
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Slf4j
@Component
public class SetterService {
     private Bean bean;

     public SetterService(){
         System.out.println("service create");
     }
     public void test(){
         System.out.println(bean);
     }

     //通过autowired指定使用set方法完成注入
     @Autowired
    public void setDiSetterBean(Bean bean) {
         System.out.println("通过autowired指定使用set方法完成注入");
        this.bean = bean;
    }
}
