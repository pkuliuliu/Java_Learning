package com.liu.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-16.
 */
public class RegexFlags{
    public static void main(String[] args) {
        /**
         * 通过传递参数使得构建的Pattern对象具有特殊功能
         */
        Pattern p = Pattern.compile("^java",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
        Matcher matcher = p.matcher("java has regex\nJava jas regex\n"+
        "JAVA has pretty good regular expressions\n"+
        "Regular expressions are in Java");
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
