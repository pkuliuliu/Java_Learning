package com.liu.demo.filedemo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-17.
 */
public class DirList3 {
    public static void main(String[] args) {
        String regx="\\w*\\.java";
        System.out.println(regx);
        File path = new File("/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/filedemo/");
        System.out.println(args[0]);
        String[] list = path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return Pattern.compile(args[0]).matcher(name).matches();
            }
        });
        System.out.println(Arrays.toString(list));
    }
}
