package com.ctf.lab.spring.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Aspect切面
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@EnableAspectJAutoProxy
@Aspect
public class AspectJAop {

    @Before("execution(* com.ctf.lab.spring.aop.UserService.test(..))")
    public void before(){
        System.out.println("前置通知");
    }


    public void test(){
        System.out.println("执行正常业务");
    }

    @After("execution(* com.ctf.lab.spring.aop.UserService.test(..))")
    public void after(){
        System.out.println("后置增强,不管正常或异常都会执行");
    }

//    @Around("execution(* com.ctf.laboratory.spring.aop.AspectJAop.test(..))")
//    public void around(){
//        System.out.println("环绕增强");
//    }

    @AfterThrowing("execution(* com.ctf.lab.spring.aop.UserService.test(..))")
    public void afterthrows(){
        System.out.println("异常抛出增强");
    }

    @AfterReturning("execution(* com.ctf.lab.spring.aop.UserService.test(..))")
    public void afterReturning(){
        System.out.println("正常退出的后置增强");
    }

}
