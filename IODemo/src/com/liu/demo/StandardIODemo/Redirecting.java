package com.liu.demo.StandardIODemo;

import javax.print.DocFlavor;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-19.
 * 1.定义一个读文件的字节流对象BufferedInputStream
 * 2.定义一个输出字节流
 * 3.重定向标准输入和输出（分别只想定义的两个字节流）
 * 4. 封装一个对System.in的输入流
 */
public class Redirecting {
    public static void main(String[] args) throws IOException{
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/com/liu/demo/StandardIODemo/Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("data/out.dat")));

        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//封装System.in
        String line;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
        in.close();
        out.close();
    }
}
