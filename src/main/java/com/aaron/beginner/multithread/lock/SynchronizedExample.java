package com.aaron.beginner.multithread.lock;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-02
 */
public class SynchronizedExample
{
    public static void main(String[] args)
    {
        /*SynchronizedExample example = new SynchronizedExample();

        example.testMethodLock01();
        example.testMethodLock02();*/

        new Thread(() ->
        {
            for (int i = 0; i < 10; i++)
            {
                int temp = i++;
                System.out.println(temp);
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException e)
                {

                }
            }
        }).start();
    }


    /**
     * synchronized修饰实例方法时，锁的是对象
     * testMethodLock01和testMethodLock02同时都是用synchronized修饰方法，
     * 所以他们用的都是同一把对象锁，所以他们不能在同一个对象中同时执行
     */
    synchronized void testMethodLock01()
    {
        for (; ; )
        {
            System.out.println("=====this is testMethodLock01");

        }
    }


    synchronized void testMethodLock02()
    {
        for (; ; )
        {
            System.out.println("this is testMethodLock02=====");
        }
    }


    /**
     * synchronized修饰代码块
     */
    void testCodeBlockLock01()
    {
        synchronized (this)
        {
            for (; ; )
            {
                System.out.println("this is testCodeBlockLock01=====");
            }
        }
    }


    void testCodeBlockLock02()
    {
        synchronized (this)
        {
            for (; ; )
            {
                System.out.println("=====this is testCodeBlockLock02");
            }
        }
    }


    static synchronized void testClassLock01()
    {
        for (; ; )
        {
            System.out.println("=====this is testClassLock01");
        }
    }


    static synchronized void testClassLock02()
    {
        for (; ; )
        {
            System.out.println("this is testClassLock02=====");
        }
    }

}
