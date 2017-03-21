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


    public static void main(String[] args)
    {
        final PrintOddAndEvenNumber printNumber = new PrintOddAndEvenNumber();

        new Thread(() ->
        {
            while (true)
            {
                synchronized (printNumber)
                {
                    if (printNumber.number % 2 == 0)
                    {
                        //唤醒其他线程
                        try
                        {
                            printNumber.wait();
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
                        printNumber.notify();
                    }

                }
            }
        }, "打印偶数线程").start();

        new Thread(() ->
        {

            while (true)
            {
                synchronized (printNumber)
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

                        printNumber.notify();
                    }
                    else
                    {
                        try
                        {
                            printNumber.wait();
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
