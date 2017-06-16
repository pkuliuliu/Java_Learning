package com.liu.demo;

/**
 * Created by liu on 17-6-15.
 */
public class Immutable {
    public static void main(String[] args) {
        String q = "abc";
        System.out.println(q);
        String qq = upcase(q);
        System.out.println(qq);
        System.out.println(qq);
        System.out.println(q==qq.toLowerCase());//false
        System.out.println(q.equals(qq.toLowerCase()));//true String 的equals函数比较的是每个值
        System.out.println(q.hashCode()==qq.toLowerCase().hashCode());//true  String的hashcode是每个值的hashcode的组合
    }

    public static String upcase(String s){
        return s.toUpperCase();
    }
}
