package com.ctf.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ForkJoinPoolFactory {

    public static ForkJoinPoolFactory getInstance(){
        return new ForkJoinPoolFactory();
    }

    public ForkJoinPool getBizForkJoinPool(){
        return get(8,"default");
    }

    public ForkJoinPool getBizForkJoinPool(String threadName){
        return get(threadName);
    }

    public ForkJoinPool getBizForkJoinPool(int parallelism,String threadName){
        return get(parallelism,threadName);
    }

    //自定义线程池
    private static class DefaultForkJoinPoolThread extends ForkJoinWorkerThread {
        protected DefaultForkJoinPoolThread(ForkJoinPool pool) {
            super(pool);
        }
    }

    /**
     * 获取线程创建工厂
     * @param trheadName 线程名称
     * @return
     */
    private ForkJoinPool.ForkJoinWorkerThreadFactory getForkJoinFactory(String trheadName){
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = new ForkJoinPool.ForkJoinWorkerThreadFactory() {
            final AtomicLong count = new AtomicLong(0);
            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
                DefaultForkJoinPoolThread thread = new DefaultForkJoinPoolThread(pool);
                thread.setName(trheadName+"-"+count.getAndIncrement());
                return thread;
            }
        };
        return factory;
    }

    /**
     * 获取并行线程池
     * @param trheadName 线程名称
     * @return
     */
    public ForkJoinPool get(String trheadName){
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = getForkJoinFactory(trheadName);
        ForkJoinPool customThreadPool = new  ForkJoinPool(4,
                factory,
                null,
                false);
        return customThreadPool;
    }

    /**
     * 获取并行线程池
     * @param parallelism 线程数量
     * @param trheadName 线程名称
     * @return
     */
    public ForkJoinPool get(int parallelism,String trheadName){
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = getForkJoinFactory(trheadName);
        ForkJoinPool customThreadPool = new  ForkJoinPool(parallelism,
                factory,
                null,
                false);
        return customThreadPool;
    }

    public static ThreadPoolExecutor executor  = new ThreadPoolExecutor(256, 300+1, 10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10000));


    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        // 每500条数据开启一条线程
        int threadSize = 4;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;

        for (int i=0;i<threadNum;i++){
            List<String> list1 = ListUtil.sub(list,threadSize * i, threadSize * (i + 1));
            System.out.println(JSONUtil.toJsonStr(list1));
        }
    }
}
