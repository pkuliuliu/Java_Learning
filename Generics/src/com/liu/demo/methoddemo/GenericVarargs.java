package com.liu.demo.methoddemo;

import java.util.*;

/**
 * Created by liu on 17-6-17.
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args){
        List<T> list = new ArrayList<T>();
        for(T item :args){
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = makeList(1,2,3,4,5);
        System.out.println(list);

        String [] strs = {"1","4","2","5","3"};
        Arrays.sort(strs);
        System.out.println(Arrays.toString(strs));
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0- o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(strs));
    }
}
