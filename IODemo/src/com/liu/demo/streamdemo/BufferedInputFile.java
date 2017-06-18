package com.liu.demo.streamdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by liu on 17-6-18.
 * 缓冲文件输入
 * 使用以String或File对象作为文件名的FileReader
 * 为了提高速度，将产生的引用传给一个BufferedReader构造器
 */
public class BufferedInputFile {

    public static String read(String filename) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();//StringBuilder 底层用的char[]数组实现
        while((s=bufferedReader.readLine())!=null){
            sb.append(s+"\n");
        }
        bufferedReader.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(read("src/com/liu/demo/streamdemo/BufferedInputFile.java"));
    }
}
