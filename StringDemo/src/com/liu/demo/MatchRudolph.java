package com.liu.demo;

/**
 * Created by liu on 17-6-16.
 */
public class MatchRudolph {
    public static void main(String[] args) {
        /**
         * [rR]匹配r或者R中的任意一个
         * [R.*]R后面跟着任意个任意字符
         */
        for (String pattern :
                new String[]{"Rudolph","[rR]udolph","[rR][aeiou][a-z]ol.*","R.*"}) {
            System.out.println("Rudolph".matches(pattern));
            /**
             * true
             */
        }

        System.out.println("R".matches("R.+"));//false
        System.out.println("R".matches("R.?"));//true
        System.out.println("R".matches("R.+"));//false
    }
}