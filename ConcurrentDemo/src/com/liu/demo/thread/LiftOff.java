package com.liu.demo.thread;

/**
 * Created by liu on 17-6-19.
 */
public class LiftOff implements Runnable{
    protected int countDown = 10;//default
    private static int taskCount=0;
    private final int id = taskCount++;

    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown>0?countDown:"LiftDown") + ")";
    }
    @Override
    public void run() {
        while(countDown-->0)
            System.out.print(status()+" ");
            Thread.yield();
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(new LiftOff()).start();
            System.out.println("waiting for liftoff "+i);
        }
    }
}

