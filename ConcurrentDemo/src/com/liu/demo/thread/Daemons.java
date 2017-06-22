package com.liu.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by liu on 17-6-21.
 */
public class Daemons {
    public static void main(String[] args) throws Exception{
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = "+d.isDaemon()+", ");
        TimeUnit.MILLISECONDS.sleep(100);
    }
}

class DaemonSpawn implements Runnable{
    @Override
    public void run() {
        while(true){
            Thread.yield();
        }
    }
}

class Daemon implements Runnable{
    private Thread[] threads = new Thread[10];
    @Override
    public void run() {
        for(int i=0;i< threads.length;i++){
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println("DaemonSpawn "+i+" started. ");
        }
        for(int i=0;i< threads.length;i++){
            System.out.println("thread["+i+"].isDaemon() = "+threads[i].isDaemon());
        }
        while(true){
            Thread.yield();
        }
    }
}