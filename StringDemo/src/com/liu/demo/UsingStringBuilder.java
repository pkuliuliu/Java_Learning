package com.liu.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by liu on 17-6-15.
 */
public class UsingStringBuilder {
    public static Random rand = new Random(47);
    public static String implict(){
        Calendar calendar = Calendar.getInstance();
        int flag = calendar.get(calendar.MILLISECOND);
        String str = "";
        for(int i=0;i<100000;i++){
            str+=i;
        }
        System.out.println(calendar.get(calendar.MILLISECOND)-flag);
        return str;
    }

    public static String explict(){
        Calendar calendar = Calendar.getInstance();
        long flag = calendar.getTime().getTime();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<100000;i++){
            stringBuffer.append(i);
        }
        System.out.println(calendar.getTime().getTime()-flag);
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        implict();
        explict();
    }
}
