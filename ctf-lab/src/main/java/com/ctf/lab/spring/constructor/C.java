package com.ctf.lab.spring.constructor;

import java.util.function.Supplier;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class C implements Supplier<C> {
    @Override
    public C get() {
        System.out.println("Supplier");
        return new C();
    }
}
