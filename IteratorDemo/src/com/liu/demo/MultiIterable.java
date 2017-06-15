package com.liu.demo;

import java.util.*;

/**
 * Created by liu on 17-6-14.
 * iterable interface
 * iterator interface
 */
public class MultiIterable extends IterableClass{
    public Iterator<String> reversed(){
        return new Iterator<String>() {
            int current = words.length - 1;
            @Override
            public boolean hasNext() {
                return current > 0;
            }

            @Override
            public String next() {
                return words[current--];
            }
        };
    }

    public Iterable<String> randomized(){
        return new Iterable<String>(){
            @Override
            public Iterator<String> iterator() {
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled,new Random(47));
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        Iterator<String> iter = new MultiIterable().iterator();
        Iterator<String> reversedIter = new MultiIterable().reversed();
        Iterable<String> shuffledIter = new MultiIterable().randomized();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        while(reversedIter.hasNext()){
            System.out.println(reversedIter.next());
        }

        for (String v :
                shuffledIter) {
            System.out.println(v);
        }
    }
}

class IterableClass implements Iterable<String>{
    protected String[] words = "I am learning Java,hello World".split(" |,");
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int size = words.length;
            int current = 0;
            @Override
            public boolean hasNext() {
                return current<size;
            }

            @Override
            public String next() {
                return words[current++];
            }
        };
    }
}
