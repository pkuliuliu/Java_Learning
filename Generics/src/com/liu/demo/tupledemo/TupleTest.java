package com.liu.demo.tupledemo;

/**
 * Created by liu on 17-6-17.
 */
public class TupleTest {
    static TwoTuple<String,Integer> g(){
        return new TwoTuple<>("hi",47);
    }

    static ThreeTuple<Amphibian,String,Integer> f(){
        return new ThreeTuple<>(new Amphibian(),"hi",47);
    }

    static ThreeTuple<Amphibian,Vehicle,String> h(){
        return new ThreeTuple<>(new Amphibian(),new Vehicle(),"i am learning java");
    }
    public static void main(String[] args) {
        System.out.println(g());
        System.out.println(f());
        System.out.println(h());
    }
}
class Amphibian{
    @Override
    public String toString() {
        return "amphibian";
    }
}
class Vehicle{
    @Override
    public String toString() {
        return "vehicle";
    }
}