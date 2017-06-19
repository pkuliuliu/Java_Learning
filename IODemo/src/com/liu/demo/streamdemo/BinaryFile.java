package com.liu.demo.streamdemo;

import java.io.*;

/**
 * Created by liu on 17-6-19.
 */
public class BinaryFile {
    public static byte[] read(File bfile) throws IOException{
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(bfile));
        byte[] bytes = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(bytes);
        bufferedInputStream.close();
        return bytes;
    }

    public static byte[] read(String bfileName) throws IOException{
        return read(new File(bfileName));
    }

    public static void main(String[] args) {

    }
}