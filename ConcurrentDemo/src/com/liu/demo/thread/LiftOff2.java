package com.liu.demo.thread;

/**
 * Created by liu on 17-6-19.
 */
public class LiftOff2 extends Thread{
    private static int taskCount = 0;
    private final int id = taskCount++;
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("id: "+id+","+i+";");
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new LiftOff2().start();
        }
    }
}
