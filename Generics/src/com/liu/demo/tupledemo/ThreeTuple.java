package com.liu.demo.tupledemo;

/**
 * Created by liu on 17-6-17.
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple(A a,B b,C c){
        super(a,b);
        this.third = c;
    }

    @Override
    public String toString() {
        return "first:"+first+",second:"+second+",third:"+third;
    }
}
