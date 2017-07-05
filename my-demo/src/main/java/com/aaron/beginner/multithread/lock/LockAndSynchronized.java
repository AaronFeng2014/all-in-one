package com.aaron.beginner.multithread.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发包下的Lock和synchronized性能比较
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-01
 */
public class LockAndSynchronized
{

    Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException
    {
        LockAndSynchronized lockAndSynchronized = new LockAndSynchronized();

        int size = 5000;
        CountDownLatch latch = new CountDownLatch(size);
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++)
        {
            new Thread(() ->
            {
                for (int a = 0; a < 100; a++)
                {
                    lockAndSynchronized.generateRandom(2000000);
                }

                latch.countDown();
            }

            ).start();
        }

        latch.await();
        long end = System.currentTimeMillis();

        CountDownLatch latchByLock = new CountDownLatch(size);

        long startLock = System.currentTimeMillis();
        for (int i = 0; i < size; i++)
        {
            new Thread(() ->
            {
                for (int a = 0; a < 100; a++)
                {
                    lockAndSynchronized.generateRandomByLock(2000000);
                }

                latchByLock.countDown();
            }

            ).start();
        }

        latchByLock.await();
        System.out.println("耗时：" + (end - start));
        System.out.println("LOCK耗时：" + (System.currentTimeMillis() - startLock));

    }


    private synchronized void generateRandom(int scope)
    {
        System.out.println(Thread.currentThread().getName() + "随机数：" + new Random().nextInt(scope));
    }


    private void generateRandomByLock(int scope)
    {

        try
        {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "LOCK随机数：" + new Random().nextInt(scope));
        }
        finally
        {
            lock.unlock();
        }
    }

}
