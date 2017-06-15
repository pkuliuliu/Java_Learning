package com.liu.demo;

/**
 * Created by liu on 17-6-15.
 */
public class Immutable {
    public static void main(String[] args) {
        String q = "123";
        System.out.println(q);
        String qq = upcase(q);
        System.out.println(qq);
        System.out.println(qq);
        System.out.println(q==qq);
        System.out.println(q.equals(qq));

    }

    public static String upcase(String s){
        return s.toUpperCase();
    }
}
