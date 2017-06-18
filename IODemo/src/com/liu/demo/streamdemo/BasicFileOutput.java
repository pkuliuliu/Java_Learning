package com.liu.demo.streamdemo;

import java.io.*;

/**
 * Created by liu on 17-6-18.
 * 首先，创建一个与指定文件链接的FileWriter。然后，通过BufferedWriter将其包装起来用缓冲区输出
 * 然后再用用PrintWriter包装输出到新文件
 */
public class BasicFileOutput {
    static String file = "data/BasicFileOutput.out";

    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/liu/demo/streamdemo/BasicFileOutput.java"));

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String line;
        int i=0;
        while((line = bufferedReader.readLine())!=null){
            printWriter.write((i++) + line + "\n");
        }
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
    }
}
