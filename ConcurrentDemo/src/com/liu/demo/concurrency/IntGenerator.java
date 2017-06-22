package com.liu.demo.concurrency;

/**
 * Created by liu on 17-6-21.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){canceled = true;}
    public boolean isCanceled(){return canceled;}
}
