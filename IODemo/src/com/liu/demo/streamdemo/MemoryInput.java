package com.liu.demo.streamdemo;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Created by liu on 17-6-18.
 * 从内存输入
 * 读入一个String来创建一个StringReader，然后调用read()每次读一个字符，并把它发送到控制台
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException{
        String str = "I am learning Java\n I am learning Java\n I am learning Java\n I am learning Java\n I am learning Java\n";
        StringReader stringReader = new StringReader(str);
        char[] buff = new char[20];
        while(stringReader.read(buff)!=-1){
            System.out.print(buff);
        }
//        int ch;
//        while((ch=stringReader.read())!=-1){
//            System.out.print((char) ch);
//        }
    }
}
