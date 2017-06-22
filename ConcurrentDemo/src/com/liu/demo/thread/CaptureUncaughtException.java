package com.liu.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by liu on 17-6-21.
 * 由于线程的本质特性，是得你不能捕获从线程中逃逸的异常，一旦异常逃出任务的run()方法，就会向外传播到控制台，除非你采用特殊的步骤捕获这种错误的异常。、、
 *
 * 为了解决这个问题，我们要修改Executor产生线程的方式，Thread.UncaughtExceptionHandler是Java SE5红的新接口，它允许你在每个Thread对想上都附着一个异常处理器。
 * Thread.UncaughtExceptionHandler.uncaughtException()会在线程因未捕获的异常而临近死亡时被调用。
 * 为了使用它，我们创建爱你一个新类型的ThreadFactory，它将在每个新创建的Thread对象上附着一个Thread.UncaughtExveptionHandler。我们将这个工厂传递给Executors创建新的ExecutorService方法。
 */
public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                Thread thread = Thread.currentThread();
//                System.out.println("run() by annonymous class "+thread);
//                System.out.println("eh = "+ thread.getUncaughtExceptionHandler());
//                throw new RuntimeException();
//            }
//        });
    }
}

class ExceptionThread implements Runnable{
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by "+t);
        System.out.println("eh = "+t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught "+e);
    }
}


class HandlerThreadFactory implements ThreadFactory{
    //创建一个工厂类。。为每个线程附着一个异常处理器（new MyUncaughtExceptionHandler()）
    //MyUncaughtExceptionHandler类中含有一个捕获异常的方法，会自动捕获异常
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+" creating new Thread");
        Thread thread = new Thread(r);
        System.out.println("created "+thread);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
        System.out.println("eh = "+thread.getUncaughtExceptionHandler());
        return thread;
    }
}