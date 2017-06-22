package com.liu.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liu on 17-6-21.
 */
public class EvenChecker implements Runnable{
    private IntGenerator intGenerator;
    private final int id;
    public EvenChecker(IntGenerator intGenerator,int id){
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while(!intGenerator.isCanceled()){
            int val = intGenerator.next();
            if(val%2!=0){
                System.out.println(val + " is not even");
                intGenerator.cancel();
            }else{
                System.out.println(val + " is even");
            }
        }
    }

    public static void test(IntGenerator intGenerator,int count){
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i =0;i<count;i++){
            exec.execute(new EvenChecker(intGenerator,i));
        }
        exec.shutdown();
    }
    public static void test(IntGenerator intGenerator){
        test(intGenerator,10);
    }
}
