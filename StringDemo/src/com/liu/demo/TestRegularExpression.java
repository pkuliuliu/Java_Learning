package com.liu.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-16.
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("Usage:\njava TestRegularExpression characterSequence regularExpression");
            System.exit(0);
        }
        System.out.println("Input: \""+args[0]+"\"");

        for(String arg :args){
            System.out.println("Regular expression:\""+arg+"\"");
            Pattern p  = Pattern.compile(arg);
            Matcher matcher = p.matcher(args[0]);
            while(matcher.find()){
                System.out.println("Match \""+matcher.group()+"\" at positions "+ matcher.start()+","+(matcher.end()-1));
            }
        }
        /**
         * matched()--整个字符串是否匹配正则表达式
         * lookingat()--字符串的始部分是否匹配正则表达式
         *
         */
        Pattern pattern = Pattern.compile("abc");
        Matcher matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.matches());
        matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.lookingAt());
        matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.find());
        matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.find(0));
    }
}
