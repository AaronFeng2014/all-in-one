package com.aaron.beginner.multithread.volatiletest;

import java.util.concurrent.CountDownLatch;

/**
 * volatile变量在如今的虚拟机中比较难以重现（高频的读取才有可能重现，while循环中不做任何操作）
 * 在1.2之前的jdk容易重现
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-01
 */
public class VolatileAndNonVolatileTest
{

    private volatile boolean flag;


    public static void main(String[] args) throws InterruptedException
    {

        final VolatileAndNonVolatileTest test = new VolatileAndNonVolatileTest();

        CountDownLatch latch = new CountDownLatch(2);
        Thread write = new Thread(() ->
        {
            test.write();
            latch.countDown();
        });

        Thread read = new Thread(() ->
        {
            test.read();
            latch.countDown();
        });

        read.start();

        Thread.sleep(100);

        write.start();

        latch.await();

    }


    private void read()
    {
        while (!flag)
        {
            //System.out.println("=");
            /**
             * 如果加上上面一句打印语句，在现代的jdk中（1.2以后）是重现不了可见性的
             *
             * 原因是在1.2之后的jdk，普通变量和volatile变量已经没有很明显的差别，只有在变量被很频繁的读取时，才会及时会写到主内存中
             *
             * 本例中，如果加上上面一句打印语句，虚拟机会认为flag变量没有频繁读取，不需要写回主内存
             *
             * 加上volatile后，就可以表示出很好的可见性
             */
        }

        System.out.println("end");
    }


    private void write()
    {
        flag = true;
    }

}
