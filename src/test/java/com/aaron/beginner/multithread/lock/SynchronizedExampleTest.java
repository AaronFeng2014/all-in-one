package com.aaron.beginner.multithread.lock;

import org.junit.Test;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-02
 */
public class SynchronizedExampleTest
{
    /**
     * 在同一个对象上调用两个被synchronized修饰的方法，这两个方法应该是互斥的<br/>
     * 即每次只能有一个线程执行其中某个方法，其他线程阻塞等待
     *
     * @throws Exception：异常信息
     */
    @Test
    public void testMethodLockForSameObject() throws Exception
    {
        SynchronizedExample test01 = new SynchronizedExample();

        new Thread(test01::testMethodLock01).start();

        new Thread(test01::testMethodLock02).start();

        while (true)
        {

        }
    }


    /**
     * 在不同对象上调用两个被synchronized修饰的方法，这两个方法应该都能执行<br/>
     * 因为至两个方法虽然被synchronized修饰，但是是位于不同的对象上，所以互不影响
     *
     * @throws Exception：异常信息
     */
    @Test
    public void testMethodLockForDifferentObject() throws Exception
    {
        SynchronizedExample test01 = new SynchronizedExample();
        SynchronizedExample test02 = new SynchronizedExample();

        new Thread(test01::testMethodLock01).start();

        new Thread(test02::testMethodLock02).start();

        while (true)
        {

        }
    }


    @Test
    public void testCodeBlockLock01() throws Exception
    {
        SynchronizedExample test01 = new SynchronizedExample();

        new Thread(test01::testCodeBlockLock01).start();

        new Thread(test01::testCodeBlockLock02).start();

        while (true)
        {

        }
    }


    @Test
    public void testCodeBlockLockAndMethodLock() throws Exception
    {
        SynchronizedExample test01 = new SynchronizedExample();

        new Thread(test01::testCodeBlockLock01).start();

        new Thread(test01::testMethodLock01).start();

        while (true)
        {

        }
    }


    @Test
    public void testCodeBlockLock02() throws Exception
    {
        SynchronizedExample test01 = new SynchronizedExample();
        SynchronizedExample test02 = new SynchronizedExample();

        new Thread(test01::testCodeBlockLock01).start();

        new Thread(test02::testCodeBlockLock02).start();

        while (true)
        {

        }

    }


    @Test
    public void testClassLock01() throws Exception
    {
        SynchronizedExample test01 = new SynchronizedExample();

        new Thread(test01::testCodeBlockLock01).start();

        new Thread(SynchronizedExample::testClassLock01).start();

        while (true)
        {

        }
    }


    @Test
    public void testClassLock02() throws Exception
    {

        new Thread(SynchronizedExample::testClassLock01).start();

        new Thread(SynchronizedExample::testClassLock02).start();

        while (true)
        {

        }
    }

}