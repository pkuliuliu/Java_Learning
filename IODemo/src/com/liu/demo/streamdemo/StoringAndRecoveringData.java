package com.liu.demo.streamdemo;

import java.io.*;

/**
 * Created by liu on 17-6-18.
 * 使用DataOutputStream输出写入，然后使用DataInputStream按照写入的顺序读
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException{
        String filename = "data/data.dat";
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dataOutputStream.writeDouble(3.1415926);
        dataOutputStream.writeUTF("That was pi");
        dataOutputStream.writeDouble(1.41413);
        dataOutputStream.writeUTF("that is root of 2");
        dataOutputStream.flush();
        dataOutputStream.close();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        dataInputStream.close();
    }
}