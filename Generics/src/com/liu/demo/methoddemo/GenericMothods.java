package com.liu.demo.methoddemo;

/**
 * Created by liu on 17-6-17.
 */
public class GenericMothods {
    //必须在返回值类型前加  <T>，此举类似一个声明，
    public <T> void f(T x){
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMothods gm = new GenericMothods();
        gm.f(" ");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f("c");
        gm.f(gm);
    }
}
