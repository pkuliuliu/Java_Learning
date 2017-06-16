package com.liu.demo;

/**
 * Created by liu on 17-6-16.
 */
public class IntergeMatch {
    public static void main(String[] args) {

        /**
         * -?表示可能有一个‘-’，或者一个没有
         * \d表示数字。\d+表示有一个或多个数字，，\d?表示有0或1个数字.。。。d--digit
         *
         */
        System.out.println("-1234".matches("-?\\d"));
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("1234".matches("-?\\d+"));
        System.out.println("+1234".matches("\\+?\\d+"));
        System.out.println("+1234".matches("(-|\\+)?\\d+"));
    }
}
