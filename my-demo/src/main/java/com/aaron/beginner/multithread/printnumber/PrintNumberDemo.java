package com.aaron.beginner.multithread.printnumber;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.concurrent.CountDownLatch;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-01
 */
public class PrintNumberDemo
{

    private static final String PARENT_PATH = "C:/Users/Aaron/Desktop/";

    static final int LAST_NUMBER = 10000;

    static int currentIndex = 1;


    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 1; i <= 1; i++)
        {
            try
            {
                OutputStream outputStream = new FileOutputStream(PARENT_PATH + i + ".txt", true);
                Object threadLock = new Object();
                new Thread(new OddThread(threadLock, outputStream, latch), "偶数线程" + i).start();
                Thread.sleep(1000);
                new Thread(new EvenThread(threadLock, outputStream, latch), "奇数线程" + i).start();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        latch.await();
        System.out.println("all job has been done!");
    }

}


class OddThread implements Runnable
{
    private final Object threadLock;

    private OutputStream outputStream;

    private CountDownLatch latch;


    OddThread(Object threadLock, OutputStream outputStream, CountDownLatch latch)
    {
        this.threadLock = threadLock;
        this.outputStream = outputStream;
        this.latch = latch;
    }


    @Override
    public void run()
    {
        while (true)
        {
            synchronized (threadLock)
            {
                if (PrintNumberDemo.currentIndex > PrintNumberDemo.LAST_NUMBER)
                {
                    break;
                }
                if (PrintNumberDemo.currentIndex % 2 == 0)
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName() + "--->" + PrintNumberDemo.currentIndex);
                        outputStream.write(PrintNumberDemo.currentIndex++);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    threadLock.notify();
                }
                else
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName() + "--->等待");

                        threadLock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }

        try
        {
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        latch.countDown();
    }
}


class EvenThread implements Runnable
{
    private final Object threadLock;

    private OutputStream outputStream;

    private CountDownLatch latch;


    EvenThread(Object threadLock, OutputStream outputStream, CountDownLatch latch)
    {
        this.threadLock = threadLock;
        this.outputStream = outputStream;
        this.latch = latch;
    }


    @Override
    public void run()
    {
        while (true)
        {
            synchronized (threadLock)
            {
                if (PrintNumberDemo.currentIndex > PrintNumberDemo.LAST_NUMBER)
                {
                    break;
                }
                if (PrintNumberDemo.currentIndex % 2 == 0)
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName() + "--->等待");
                        threadLock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName() + "--->" + PrintNumberDemo.currentIndex);
                        outputStream.write(PrintNumberDemo.currentIndex++);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    threadLock.notify();
                }
            }
        }

        try
        {
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        latch.countDown();
    }
}