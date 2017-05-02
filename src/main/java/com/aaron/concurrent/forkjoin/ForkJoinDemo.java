package com.aaron.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-03
 */
public class ForkJoinDemo
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> result = pool.submit(new CalculateSumTask(1, 10000));

        System.out.println("结果是：" + result.get());

        System.out.println("计算耗时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 10000; i++)
        {
            sum += i;
        }

        System.out.println("结果是：" + sum);

        System.out.println("计算耗时：" + (System.currentTimeMillis() - start));
    }
}
