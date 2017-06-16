package com.liu.demo;

/**
 * Created by liu on 17-6-16.
 */
public class Replacing {
    static String s=Splitting.knights;
    public static void main(String[] args) {
        /**
         * f\w+替换以f开头的几个字符
         * shrubbery|tree|herring替换三种字符
         */
        System.out.println(s.replaceFirst("f\\w+","located"));
        System.out.println(s.replaceAll("shrubbery|tree|herring","banana"));
    }
}
