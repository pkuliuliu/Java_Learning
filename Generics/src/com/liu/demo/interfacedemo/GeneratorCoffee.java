package com.liu.demo.interfacedemo;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by liu on 17-6-17.
 */
public class GeneratorCoffee implements IGenerator<Coffee>,Iterable<Coffee>{
    private Class[] types = new Class[]{Latte.class,Mocha.class,Cappuccino.class,Breve.class,Americano.class};
    private Random random = new Random(47);
    private int size;
    public GeneratorCoffee(int size){
        this.size = size;
    }
    @Override
    public Coffee next() {
        try{
            return (Coffee)types[random.nextInt(types.length)].newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new Iterator<Coffee>() {
            int size = GeneratorCoffee.this.size;
            @Override
            public boolean hasNext() {
                return size>0;
            }

            @Override
            public Coffee next() {
                try {
                    size--;
                    return (Coffee)types[random.nextInt(types.length)].newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    public static void main(String[] args) {
        GeneratorCoffee generatorCoffee = new GeneratorCoffee(10);
        for(int i =0;i<10;i++){
            System.out.println(generatorCoffee.next());
        }

        Iterator<Coffee> iter = generatorCoffee.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
