package com.liu.demo.streamdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liu on 17-6-18.
 * 文本文件输出的快捷方式--
 * java SE5 在PrintWriter中添加了一个辅助构造器，使得你不必在每次希望创建文本文件并向其中写入时，都去执行所有的装饰操作。
 * 但实际上是在进行缓存
 */
public class FileOutputShortCut {
    static String filename = "data/BasicFileOutputShortCut.out";

    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/streamdemo/FileOutputShortCut.java"));
        PrintWriter printWriter = new PrintWriter(filename);
        int i=0;
        String line;
        while((line = bufferedReader.readLine())!=null){
            printWriter.write((i++)+" "+line+"\n");
        }
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
    }
}