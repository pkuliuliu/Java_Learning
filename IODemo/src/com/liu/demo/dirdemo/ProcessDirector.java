package com.liu.demo.dirdemo;

import java.io.File;
import java.io.IOException;

/**
 * Created by liu on 17-6-17.
 */
public class ProcessDirector {
    //嵌套一个接口
    public interface Strategy{
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public ProcessDirector(Strategy strategy,String ext){
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args){
        try {
            for(String arg:args){
                File file=new File(arg);
                if(file.isDirectory()){
                    processDirectory(file);
                }else {
                    if(!arg.endsWith("."+ext)){
                        arg+="."+ext;
                    }
                    strategy.process(new File(arg).getCanonicalFile());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void processDirectory(File root) throws IOException{
        for(File file:TreeInfo.walk(root.getAbsolutePath(),".*\\."+ext)){
            strategy.process((file.getCanonicalFile()));
        }
    }

    public static void main(String[] args) throws IOException{
        new ProcessDirector(new Strategy(){
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        },"java").start(new String[]{"."});
    }
}
