package com.liu.demo.dirdemo;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by liu on 17-6-17.
 */
public class PPrint {
    public static String pformat(Collection<?> collection){
        if(collection.size()==0)
            return "[]";
        StringBuilder result = new StringBuilder("[");
        for(Object elem : collection){
            if(collection.size()!=1){
                result.append("\n");
            }
            result.append(elem);
        }
        if(collection.size()!=1){
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }
    public static void pprint(Collection<?> collection){
        System.out.println(pformat(collection));
    }

    public static void pprint(Object[] objects){
        System.out.println(Arrays.toString(objects));
    }
}
