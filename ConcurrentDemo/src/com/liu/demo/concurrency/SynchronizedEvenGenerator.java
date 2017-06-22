package com.liu.demo.concurrency;

/**
 * Created by liu on 17-6-21.
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {

        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
