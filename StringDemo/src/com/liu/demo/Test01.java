package com.liu.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-16.
 */
public class Test01 {
    public static void main(String[] args) {
        String s = "Java now has regular expressions";
        String[] regulars = new String[]{"^Java","\\Breg.*","\\breg","n.w\\s+h(a|i)s","s?","s*","s{4}","s{1}.","s{0,3}","s$",};
        Matcher matcher=null;
        for(String regx:regulars){
            matcher = Pattern.compile(regx).matcher(s);
            if(matcher.find())
                System.out.println(regx+":true"+"--"+matcher.group());
            else
                System.out.println(regx+":false");
        }

        matcher = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b").matcher("Arline ate eight apples an one orange while Anita hadn't any");
        while(matcher.find())
            System.out.println(matcher.group());
    }
}
