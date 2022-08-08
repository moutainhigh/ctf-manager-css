package com.ctf.lab.spring.bean;

import org.springframework.context.SmartLifecycle;

/**
 * {@link org.springframework.context.SmartLifecycle}继承了Lifecycle和Phased
 * 相比Lifecycle多了一个优先级功能,并且可以控制是否自动调用
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ISmartLifecycle2 implements SmartLifecycle {
    boolean isRunning;
    @Override
    public void start() {
        isRunning = true;
        System.out.println("ISmartLifecycle2启动");
    }

    @Override
    public void stop() {
        isRunning = false;
        System.out.println("ISmartLifecycle2结束");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("容器停止时调用的方法");
       stop();
       callback.run();
    }

    @Override
    public int getPhase() {
        System.out.println("值越大优先级越低,默认最低");
        return 2;
    }
}
