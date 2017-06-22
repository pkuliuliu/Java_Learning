package com.liu.demo.thread;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by liu on 17-6-20.
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<>();
        for(int i=0;i<10;i++){
            result.add(executorService.submit(new TaskWithResult(i)));
        }
        for(Future<String> stringFuture : result){
            try{
                //get() blocks until completion;
                System.out.println(stringFuture.get());
            }catch (InterruptedException e){
                System.out.println(e);
                return;
            }catch (ExecutionException e){
                System.out.println(e);
            }finally {
                executorService.shutdown();//neccessary
            }
        }
    }
}


class TaskWithResult implements Callable<String>{

    private int taskid;
    public TaskWithResult(int taskid){
        this.taskid = taskid;
    }
    @Override
    public String call() throws Exception {
        return "result of task: " + taskid;
    }
}