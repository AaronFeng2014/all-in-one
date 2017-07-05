package com.aaron.beginner.multithread.exchange;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-27
 */
public class CountDownLatchDemo
{
    private static final Log LOG = LogFactory.getLog(CountDownLatchDemo.class);

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    private static final int SPLIT_SIZE = 500_000;

    private static final int DATA_SIZE = 50_000_000;

    private static final List<Integer> DATA_LIST;


    static
    {
        long start = System.currentTimeMillis();
        DATA_LIST = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < DATA_SIZE; i++)
        {
            DATA_LIST.add(random.nextInt(DATA_SIZE));
        }
        LOG.info("初始化list完成，耗时：" + (System.currentTimeMillis() - start));
    }


    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        CompletionService<Integer> service = new ExecutorCompletionService<>(executor);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < DATA_SIZE / SPLIT_SIZE; i++)
        {
            service.submit(new Calculate(DATA_LIST.subList(i * SPLIT_SIZE, (i + 1) * SPLIT_SIZE)));
        }

        for (int i = 0; i < DATA_SIZE / SPLIT_SIZE; i++)
        {
            try
            {
                Integer integer = service.take().get();

                result.add(integer);
            }
            catch (Exception e)
            {
                LOG.error("线程异常", e);
            }
        }

        LOG.info("最大值是：" + Collections.max(result));
        LOG.info("计算完成，耗时：" + (System.currentTimeMillis() - start));
        executor.shutdown();
    }
}


class Calculate implements Callable<Integer>
{

    private static final Log LOG = LogFactory.getLog(Calculate.class);

    private List<Integer> list;


    Calculate(List<Integer> list)
    {
        this.list = list;
    }


    @Override
    public Integer call() throws Exception
    {
        Integer max = Collections.max(list);

        LOG.info(Thread.currentThread().getName() + "计算的最大值是：" + max);

        return max;
    }
}

