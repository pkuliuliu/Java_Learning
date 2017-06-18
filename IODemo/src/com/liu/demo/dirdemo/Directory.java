package com.liu.demo.dirdemo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by liu on 17-6-17.
 */
public final class Directory {
    public static File[] local(File dir,final String regx){
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return Pattern.compile(regx).matcher(name).matches();
            }
        });
    }

    public static File[] local(String path,final String regx){
        return local(new File(path),regx);
    }
}
