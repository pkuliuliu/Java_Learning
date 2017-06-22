package com.liu.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liu on 17-6-20.
 * 创建一个CachedThreadPool,这是一个ExecutorService对象，也是一个Executor对象
 * 用CachedThreadPool来管理多个线程
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new LiftOff());
            System.out.println("waiting for liftoff : "+i);
        }
        exec.shutdown();
    }
}
