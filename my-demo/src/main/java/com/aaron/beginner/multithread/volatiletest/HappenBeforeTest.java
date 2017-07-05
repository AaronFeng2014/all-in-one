package com.aaron.beginner.multithread.volatiletest;

import java.util.concurrent.CountDownLatch;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-01
 */
public class HappenBeforeTest
{
    private boolean flag;

    private int a = 2;


    public static void main(String[] args) throws InterruptedException
    {
        for (int i = 0; i < 1000; i++)
        {

            CountDownLatch latch = new CountDownLatch(2);
            HappenBeforeTest test = new HappenBeforeTest();

            Thread write = new Thread(test.new ThreadA(latch));
            Thread read = new Thread(test.new ThreadB(latch));

            write.start();
            read.start();

            latch.await();
        }
    }


    private class ThreadA implements Runnable
    {
        private CountDownLatch latch;


        private ThreadA(CountDownLatch latch)
        {
            this.latch = latch;
        }


        @Override
        public void run()
        {
            a = 5;
            flag = true;

            latch.countDown();
        }
    }


    private class ThreadB implements Runnable
    {

        private CountDownLatch latch;


        ThreadB(CountDownLatch latch)
        {
            this.latch = latch;
        }


        @Override
        public void run()
        {
            if (flag)
            {
                System.out.println("====" + a * a);
            }

            latch.countDown();
        }
    }
}
