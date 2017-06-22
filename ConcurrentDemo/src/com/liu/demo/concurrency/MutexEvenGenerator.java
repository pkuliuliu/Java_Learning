package com.liu.demo.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liu on 17-6-22.
 * 添加一个被互斥调用的锁，并使用lock()和unlock()方法在next()内部创建了临界资源。当你在使用lock对象时，应该注意：
 * 紧接着对lock()的调用，必须在try-finally块中的finally块中放置对unlock()的调用。
 * 注意，return语句必须在try子句中出现，以确保unlock()不会过早的放生，从而将数据暴露给了第一个任务。
 */
public class MutexEvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public int next(){
        lock.lock();
        try{
            currentEvenValue++;
            Thread.yield();
            currentEvenValue++;
            return currentEvenValue;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}