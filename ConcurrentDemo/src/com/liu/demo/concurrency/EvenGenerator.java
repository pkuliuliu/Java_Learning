package com.liu.demo.concurrency;

/**
 * Created by liu on 17-6-21.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public int next() {
        /**
         * Danger point here--此处容易发生错误，
         * 一个任务有可能在另一个任务执行第一个currentEvenValue++之后，但是还未执行第二个操作之前，滴啊用next()方法，
         * 这将会返回错误的值。
         */

        currentEvenValue++;
        Thread.yield();//由于使用的特定的操作系统和其他实现细节，EvenGenerator完成多次循环之前，这个问题都不会被发现，因此可以将yield函数放在两次操作之间。
        currentEvenValue++;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(),10);
    }
}
