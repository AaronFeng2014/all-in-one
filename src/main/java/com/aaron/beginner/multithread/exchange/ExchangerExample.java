package com.aaron.beginner.multithread.exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-04
 */
public class ExchangerExample
{
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private volatile int index;

    private Exchanger<Integer> exchanger = new Exchanger<>();


    public static void main(String[] args)
    {
        ExchangerExample example = new ExchangerExample();
        for (int i = 1; i <= 1000; i++)
        {
            example.executorService.execute(example.new TestThread(example));
        }

        example.executorService.shutdown();
    }


    private class TestThread implements Runnable
    {

        final ExchangerExample example;


        public TestThread(ExchangerExample example)
        {
            this.example = example;

        }


        @Override
        public void run()
        {
            try
            {
                //Thread.sleep(200);

                synchronized (example)
                {
                    ++index;
                }

                System.out.println(
                        "当前线程" + Thread.currentThread().getName() + "接收到另外一个线程的数据，内容：" + exchanger.exchange(index, 2, TimeUnit.SECONDS));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
