package com.liu.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by liu on 17-6-17.
 */
public class RandomList<T>{
    private List<T> list;
    public RandomList(List<T> list){
        this.list = list;
    }
    public T select() {
        return list.get(new Random().nextInt(list.size()));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("I am learning Java".split("\\W")));
        RandomList<String> randomList = new RandomList<>(list);
        for(int i=0;i<4;i++){
            System.out.print(randomList.select()+" ");
        }
        System.out.println();

        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,0));
        RandomList<Integer> integerRandomList = new RandomList<>(integerList);
        for(int i=0;i<10;i++){
            System.out.print(integerRandomList.select()+" ");
        }
    }
}
