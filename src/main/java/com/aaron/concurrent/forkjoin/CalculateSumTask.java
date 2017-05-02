package com.aaron.concurrent.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-03
 */
public class CalculateSumTask extends RecursiveTask<Long>
{
    private int begin;
    private int end;

    private static final int THRESHOLD = 1000;


    CalculateSumTask(int begin, int end)
    {
        this.begin = begin;
        this.end = end;
    }


    @Override
    protected Long compute()
    {
        long sum = 0;

        if ((end - begin) <= THRESHOLD)
        {
            sum += getResult(begin, end);
        }
        else
        {
            CalculateSumTask leftTask = new CalculateSumTask(begin, (begin + end) / 2);
            CalculateSumTask rightTask = new CalculateSumTask((begin + end) / 2 + 1, end);

            ForkJoinTask<Long> leftFork = leftTask.fork();
            ForkJoinTask<Long> rightFork = rightTask.fork();

            sum = leftFork.join() + rightFork.join();
        }
        return sum;
    }


    private long getResult(int begin, int end)
    {
        long sum = 0;

        for (int i = begin; i <= end; i++)
        {
            sum += i;
        }

        return sum;
    }
}
