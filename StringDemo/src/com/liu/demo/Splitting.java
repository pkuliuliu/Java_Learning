package com.liu.demo;

import java.util.Arrays;

/**
 * Created by liu on 17-6-16.
 */
public class Splitting {
    public static String knights = "then, when you have found the shrubbery, you must "+
            "cut down the mightiest tree in the forest..."+
            "with... a herring";
    public static void split(String regex){
        System.out.println(Arrays.toString(knights.split(regex)));

    }

    /**
     * \W+表示一个或多个非字母字符，\w+表示一个或多个字母字符
     * @param args
     */
    public static void main(String[] args) {
        split(" ");
        split("\\W+");
        split("n\\W+");
    }
}
