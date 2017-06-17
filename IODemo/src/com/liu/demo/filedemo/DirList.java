package com.liu.demo.filedemo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-17.
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File("/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/filedemo/");
        String[] list;
        //list = path.list(new DirFilter("\\w*\\.java"));
        list = path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return Pattern.compile("\\w*\\.java").matcher(name).matches();
            }
        });
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(list));
    }
}

class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter(String regx){
        pattern = Pattern.compile(regx);
    }

    public boolean accept(File dir, String name){
        return pattern.matcher(name).matches();
    }
}