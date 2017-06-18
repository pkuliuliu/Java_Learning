package com.liu.demo.streamdemo;

import java.io.*;

/**
 * Created by liu on 17-6-9.
 */
public class FileStreamDemo {

    public static void main(String[] args) {
        readFile();
        writeFile();
    }
    static void readFile(){
        InputStream inputStream = null;
        try{
             inputStream = new BufferedInputStream(new FileInputStream("data/in.dat"));
            int ch;
            while((ch = inputStream.read())!=-1){
                System.out.print((char)ch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    static void writeFile(){
        OutputStream outputStream = null;
        try{
            outputStream = new BufferedOutputStream(new FileOutputStream("data/out.dat"));
            byte[] b = new byte[20];
            for(int i=0;i<20;i++){
                b[i] = (byte)i;
            }
            outputStream.write(b);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                outputStream.flush();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
