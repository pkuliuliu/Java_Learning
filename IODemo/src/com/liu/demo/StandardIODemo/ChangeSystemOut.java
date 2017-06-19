package com.liu.demo.StandardIODemo;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liu on 17-6-19.
 */
public class ChangeSystemOut {
    public static void main(String[] args) throws IOException{
        PrintWriter printWriter = new PrintWriter(System.out,true);//true--每次输出清理缓冲区
        printWriter.println("hello world!!");
    }
}
