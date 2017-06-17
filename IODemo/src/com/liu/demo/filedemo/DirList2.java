package com.liu.demo.filedemo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-17.
 */
public class DirList2 {
    public static FilenameFilter filter(final String regx){
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return Pattern.compile(regx).matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        File path = new File("/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/filedemo/");
        String[] list = path.list(filter("\\w*\\.java"));
        System.out.println(Arrays.toString(list));
    }
}
