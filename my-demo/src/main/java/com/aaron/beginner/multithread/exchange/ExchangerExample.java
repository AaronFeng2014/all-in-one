package com.aaron.beginner.multithread.exchange;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private static final Log LOG = LogFactory.getLog(ExchangerExample.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private Exchanger<Integer> exchanger = new Exchanger<>();


    public static void main(String[] args)
    {
        ExchangerExample example = new ExchangerExample();
        for (int i = 1; i <= 100; i++)
        {
            example.executorService.execute(example.new TestThread(example, i));
        }

        example.executorService.shutdown();
    }


    private class TestThread implements Runnable
    {

        final ExchangerExample example;

        int num;


        TestThread(ExchangerExample example, int num)
        {
            this.example = example;

            this.num = num;

        }


        @Override
        public void run()
        {
            try
            {
                LOG.info("当前线程" + Thread.currentThread().getName() + "接收到另外一个线程的数据，内容：" + exchanger.exchange(num, 2, TimeUnit.SECONDS));

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
