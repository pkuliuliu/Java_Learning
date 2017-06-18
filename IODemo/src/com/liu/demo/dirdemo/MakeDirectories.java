package com.liu.demo.dirdemo;

import java.io.File;
import java.io.IOException;

/**
 * Created by liu on 17-6-17.
 */
public class MakeDirectories {
    private static void fileData(File file){
        System.out.println(
                "Absolute path: "+file.getAbsolutePath()+"\n"
                +"Can read: "+file.canRead()+"\n"
                +"Can Write: "+file.canWrite()+"\n"
                +"getName: "+file.getName()+"\n"
                +"getParent: "+file.getParent()+"\n"
                +"getPath: "+file.getPath()+"\n"
                +"length: "+file.length()+"\n"
                +"lastModified: "+file.lastModified()+"\n"
        );
        if(file.isFile()){
            System.out.println("its a file");
        }else if(file.isDirectory()){
            System.out.println("it's a directory");
        }
    }

    public static void main(String[] args) throws IOException{
        String path="/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/PipedStreamDemo.java";
        File file = new File(path);
        File newFile = new File("/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/PipedStreamDemo.java");
        if(file.exists()){
            fileData(file);
        }else {
            fileData(file);
            //file.createNewFile();
//            file.mkdir();
//            file.mkdirs();
//            file.delete();
//            file.renameTo(newFile);
        }
    }
}
