package com.liu.demo.tupledemo;

/**
 * Created by liu on 17-6-17.
 */
public class TwoTuple<A,B> {
    public final A first;
    public final B second;
    public TwoTuple(A a,B b){
        this.first=a;
        this.second=b;
    }

    @Override
    public String toString() {
        return "first:"+first+",second:"+second;
    }
}
