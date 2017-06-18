package com.liu.demo.dirdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liu on 17-6-17.
 */
public class TreeInfo implements Iterable<File>{
    public List<File> files = new ArrayList<>();
    public List<File> dirs = new ArrayList<>();
    @Override
    public Iterator<File> iterator() {
        return files.iterator();
    }
    void addAll(TreeInfo treeInfo){
        files.addAll(treeInfo.files);
        dirs.addAll(treeInfo.dirs);
    }

    @Override
    public String toString() {
        return "dirs:"+ PPrint.pformat(dirs)+"\n\nfiles:"+ PPrint.pformat(files);
    }

    static TreeInfo recurseDirs(File startDir,String regex){
        TreeInfo result = new TreeInfo();
        for(File item:startDir.listFiles()){
            if(item.isDirectory()){
                result.dirs.add(item);
                result.addAll(recurseDirs(item,regex));
            }else {
                if(item.getName().matches(regex)){
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static TreeInfo walk(String start,String regex){
        return recurseDirs(new File(start),regex);
    }
    public static TreeInfo walk(File start,String regex){
        return recurseDirs(start,regex);
    }
    public static TreeInfo walk(File start){
        return recurseDirs(start,".*");
    }
    public static TreeInfo walk(String start){
        return recurseDirs(new File(start),".*");
    }


    public static void main(String[] args) {
        System.out.println(walk("/home/liu/Workspace/Java/Java_Learning/IODemo",".*\\.java"));
    }
}



