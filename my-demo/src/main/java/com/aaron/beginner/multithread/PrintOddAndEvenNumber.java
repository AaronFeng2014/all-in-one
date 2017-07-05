package com.aaron.beginner.multithread;

/**
 * 两个线程交替打印基数和偶数
 * 重点是锁要是同一个
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-02-27
 */
public class PrintOddAndEvenNumber
{
    private int number;

    private final static Object OBJECT_LOCK = new Object();


    public static void main(String[] args)
    {
        final PrintOddAndEvenNumber printNumber = new PrintOddAndEvenNumber();

        new Thread(() ->
        {
            while (true)
            {
                synchronized (OBJECT_LOCK)
                {
                    if (printNumber.number % 2 == 0)
                    {
                        //唤醒其他线程
                        try
                        {
                            OBJECT_LOCK.wait();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        printNumber.print();

                        try
                        {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        OBJECT_LOCK.notify();
                    }

                }
            }
        }, "打印偶数线程").start();

        new Thread(() ->
        {

            while (true)
            {
                synchronized (OBJECT_LOCK)
                {
                    if (printNumber.number % 2 == 0)
                    {
                        //打印数字
                        printNumber.print();

                        try
                        {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        OBJECT_LOCK.notify();
                    }
                    else
                    {
                        try
                        {
                            OBJECT_LOCK.wait();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
            }

        }, "打印奇数线程").start();

        while (true)
        {

        }

    }


    private void print()
    {
        System.out.println(Thread.currentThread().getName() + ":" + (++number));
    }

}
