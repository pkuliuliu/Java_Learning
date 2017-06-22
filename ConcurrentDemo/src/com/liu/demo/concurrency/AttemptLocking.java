package com.liu.demo.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liu on 17-6-22.
 *
 * Reentrantlock 允许你尝试着获取但是最终未获取锁，这样如果其他人已经获取了这个锁，那你就可以决定离开去执行其他一些事情
 * 而不是等待直至这个锁被释放，就像在untimed()方法中看到的，
 * 显示的lock对象在加锁和释放锁方面，相对于内建的synchronized锁来说，还赋予了你更细粒度的控制力。
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        boolean captured = lock.tryLock();
        try{
            System.out.println("tryLock(): "+captured);
        }finally{
            if(captured)
                lock.unlock();
        }
    }

    public void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();


        new Thread(){
            {setDaemon(true);}
            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired ...");
            }
        }.start();
        Thread.yield();
        al.untimed();
        al.timed();
    }
}