package com.liu.demo.thread;

import com.liu.demo.thread.ExceptionThread;
import com.liu.demo.thread.MyUncaughtExceptionHandle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liu on 17-6-21.
 * 为了在代码中处处使用相同的异常处理器，更简单的方式是在Thread类中设置静态域，并将这个处理器设置为默认的未捕获异常处理器。
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
    }
}
