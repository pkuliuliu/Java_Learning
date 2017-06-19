package com.liu.demo.StandardIODemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by liu on 17-6-19.
 * 直接显示你所输入的每一行
 */
public class Echo {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        String line;
        while((line = br.readLine())!=null && line.length()>0){
            System.out.println(line);
        }
    }
}
