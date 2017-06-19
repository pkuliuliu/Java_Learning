package com.liu.demo.streamdemo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by liu on 17-6-19.
 */
public class TextFile extends ArrayList<String>{
    public static String read(String fileName) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static void write(String fileName,String text)throws IOException{
        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.write(text);
        printWriter.close();
    }

    public TextFile(String fileName,String spliter)throws IOException{
        super(Arrays.asList(read(fileName).split(spliter)));
        //Regular expression split() often leaves an empty
        if(get(0).equals(""))
            remove(0);
    }

    public TextFile(String fileName)throws IOException{
        this(fileName,"\n");
    }

    public void write(String fileName)throws IOException{
        PrintWriter printWriter = new PrintWriter(fileName);
        for(String item : this){
            printWriter.println(item);
        }
        printWriter.close();
    }

    public static void main(String[] args) throws IOException{
        String fileName = "/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/streamdemo/TextFile.java";
        String file = read(fileName);
        write("data/test.dat",file);
        TextFile textFile = new TextFile("data/test.dat");
        textFile.write("data/test2.dat");
        TreeSet<String> words = new TreeSet<>(new TextFile(fileName,"\\W+"));
        System.out.println(words);
    }
}
