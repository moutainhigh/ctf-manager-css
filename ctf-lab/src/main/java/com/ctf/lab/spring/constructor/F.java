package com.ctf.lab.spring.constructor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class F {



    @Autowired(required = false)
    public F(H h) {
        System.out.println("h构造器");
    }

    @Autowired(required = false)
    public F(G g){
        System.out.println("g构造器");
    }

    @Autowired(required = false)
    public F(I i){
        System.out.println("i构造器");
    }

    @Autowired(required = false)
    public F(J j){
        System.out.println("j构造器");
    }


}
