package com.aaron.enhance.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Aaron on 2016-08-29.
 */
public class ReadWriteLockTest
{

    private ReentrantReadWriteLock lock;
    private static Map<String, String> map = new HashMap<>();


    public ReadWriteLockTest()
    {
        map.put("name", "li");
        map.put("age", "14");
        map.put("tall", "189");
    }


    public static void main(String[] args) throws InterruptedException
    {
        ReadWriteLockTest test = new ReadWriteLockTest();
        test.lock = new ReentrantReadWriteLock();

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++)
            {

                new Thread(test.new ReadTest()).start();
            }
        };

        new Thread(runnable).start();
        //Thread.sleep(3000);

        Runnable write = () -> {
            for (int i = 0; i < 4; i++)
            {

                new Thread(test.new WriteTest()).start();
            }
        };
        new Thread(write).start();

    }


    class ReadTest implements Runnable
    {

        @Override
        public void run()
        {
            lock.readLock().lock();
            System.out.println("已经获取到了【读锁】");
            try
            {
                while (true)
                {
                    Set<Map.Entry<String, String>> entries = map.entrySet();

                    //Thread.sleep(1000);

                    for (Map.Entry<String, String> entry : entries)
                    {
                        System.out.println(entry.getKey() + " : " + entry.getValue());
                    }
                }

            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.readLock().unlock();
                System.out.println("已经释放了【读锁】");
            }

        }
    }

    class WriteTest implements Runnable
    {
        @Override
        public void run()
        {
            lock.writeLock().lock();
            System.out.println("已经获取到了写锁");
            try
            {
                map.clear();
                map.put("name", "fenghaixin");
                map.put("age", "25");
                //Thread.sleep(2000);
                map.put("tall", "168");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.writeLock().unlock();
                System.out.println("已经释放了【写锁】");
            }
        }
    }
}
