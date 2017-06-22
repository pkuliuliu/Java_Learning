package com.liu.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liu on 17-6-20.
 */
public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try {
            while(countDown-->0){
                System.out.print(status());
                //old style
                //Thread.sleep(100);
                //java se5/6
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.execute(new SleepingTask());
            System.out.println("waiting for liftoff "+ i);
        }
        executorService.shutdown();
    }
}
