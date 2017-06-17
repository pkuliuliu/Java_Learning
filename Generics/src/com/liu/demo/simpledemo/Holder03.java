package com.liu.demo.simpledemo;

/**
 * Created by liu on 17-6-17.
 * Generic 泛型，，能表示多种类型，，参数化类型
 * 作用一： 封装通用的处理方法
 * 作用二：在实例化时。控制参数类型，防止运行时错误
 */
public class Holder03<T> {
    private T a;
    public void set(T a){
        this.a = a;
    }
    public T get(){
        return a;
    }

    public Holder03(T a){
        this.a=a;
    }

    public static void main(String[] args) {
        Holder03<Automobile> h = new Holder03<>(new Automobile());
        Automobile a = h.get();
        //h.set("hello world"); //泛型控制了参数的类型
    }
}
