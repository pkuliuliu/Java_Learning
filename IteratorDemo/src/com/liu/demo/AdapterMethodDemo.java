package com.liu.demo;

import java.util.*;

/**
 * Created by liu on 17-6-14.
 */


class ReversibleArrayList<T> extends ArrayList<T>{
    public ReversibleArrayList(Collection<T> c){
        super(c);
    }


    public Iterator<T> reversed() {
        return new Iterator<T>() {
            int current = size() - 1;
            @Override
            public boolean hasNext() {
                return current >= 0;
            }

            @Override
            public T next() {
                T t = get(current);
                current--;
                return t;
            }
        };
    }
}

public class AdapterMethodDemo {
    public static void main(String[] args) {
        ReversibleArrayList<String> ral = new ReversibleArrayList<String>(Arrays.asList("i am learning Java".split(" ")));
        Iterator<String> iter = ral.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        List<String> list = new ArrayList<>(Arrays.<String>asList("I am learning Java".split(" ")));
        list.add("test");
        System.out.println(list);
    }
}
