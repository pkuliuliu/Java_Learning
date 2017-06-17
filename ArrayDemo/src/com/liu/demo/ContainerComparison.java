package com.liu.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liu on 17-6-16.
 */
public class ContainerComparison {
    public static void main(String[] args) {
        BeryllinumSphere[] spheres = new BeryllinumSphere[10];
        for (int i=0;i<5;i++){
            spheres[i]=new BeryllinumSphere();
        }
        System.out.println(Arrays.toString(spheres));
        System.out.println(spheres[4]);

        List<BeryllinumSphere> sphereList = new ArrayList<>();
        for(int i=0;i<5;i++){
            sphereList.add(new BeryllinumSphere());
        }
        System.out.println(sphereList);
        System.out.println(sphereList.get(4));

        int integers[] = {1,2,3,4,5};
        System.out.println(Arrays.toString(integers));
        System.out.println(integers[4]);

        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        integerList.add(97);
        System.out.println(integerList);
        System.out.println(integerList.get(4));

        List list = Arrays.asList(integers);

    }
}

class BeryllinumSphere{
    private static long counter;
    private final long id = counter++;
    public String toString(){return "Sphere "+id;}
}
