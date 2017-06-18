package com.liu.demo.streamdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by liu on 17-6-14.
 */
public class PipedStreamDemo {

    public static void main(String[] args) throws Exception{
        // create pos and pis object ,then connect them
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis =  new PipedInputStream(pos);
        // create action demo
        InputStreamRunnable in = new InputStreamRunnable(pis);
        OutStreamRunnable out = new OutStreamRunnable(pos);
        // create two thread to run the demo
        new Thread(in).start();
        new Thread(out).start();
    }
    static class InputStreamRunnable implements Runnable{
        private PipedInputStream pis;
        public InputStreamRunnable(PipedInputStream pis){
            System.out.println("i am inStream, prepared to get message");
            this.pis = pis;
        }

        @Override
        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(pis));
            try {
                System.out.println(br.readLine());
                //System.out.println(br.readLine());
                br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static class OutStreamRunnable implements Runnable{
        private PipedOutputStream pos;
        public OutStreamRunnable(PipedOutputStream pos){
            this.pos = pos;
            System.out.println("I am OutStream, prepared to print something");
        }

        @Override
        public void run() {
            try{
                pos.write("hello world\n you cant get me".getBytes());
                pos.flush();
                pos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
