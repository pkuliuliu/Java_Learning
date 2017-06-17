package com.liu.demo.simpledemo;


/**
 * Created by liu on 17-6-17.
 */
public class Holder02 {
    private Object a;
    public Holder02(Object a){
        this.a=a;
    }
    public void set(Object a){
        this.a=a;
    }
    public Object get(){
        return this.a;
    }

    public static void main(String[] args) {
        Holder02 h2 = new Holder02(new Automobile());
        Automobile a = (Automobile) h2.get();
        h2.set("hello world");
        String s = (String)h2.get();
        h2.set(1);;
        Integer x = (Integer)h2.get();
        int n = (int)h2.get();
        System.out.println(n);
    }
}
