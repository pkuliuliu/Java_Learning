package com.liu.demo.streamdemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Created by liu on 17-6-18.
 * RandomAccessFile实现了DataInput和DataOutput两种接口，支持读和写，"r",,"rw",,不支持“w”
 * seek()函数可以到处移动，修改相应的值
 */
public class UsingRandomAccessFile {
    static String file ="data/rtest.dat";
    static void display() throws IOException{
        RandomAccessFile rf = new RandomAccessFile(file,"r");
        for(int i =0;i<7;i++){
            System.out.println("Value "+i+": "+rf.readDouble());
        }
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args) throws  IOException{
        RandomAccessFile rf = new RandomAccessFile(file,"rw");
        for(int i=0;i<7;i++){
            rf.writeDouble(i*1.414);
        }
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file,"rw");
        rf.seek(5*8);
        rf.writeDouble(47.0001);//覆盖了第5个double，，覆盖了8个字节
        rf.close();
        display();
    }
}
