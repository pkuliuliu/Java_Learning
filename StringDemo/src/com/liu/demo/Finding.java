package com.liu.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-16.
 */
public class Finding {
    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");

        while(matcher.find()){
            System.out.print(matcher.group()+" ");
        }

        System.out.println();
        int i=0;
        while (matcher.find(i)){
            System.out.println(matcher.group()+" ");
            i++;
        }
    }
}
