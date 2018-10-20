package com.aaron.enhance.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC condition使用
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/12
 */
@Slf4j
public class ConditionSample
{

    private Lock lock = new ReentrantLock();

    private Condition managerCondition = lock.newCondition();

    private Condition teamLeaderCondition = lock.newCondition();

    private Condition coderCondition = lock.newCondition();

    private Phaser phaser = new Phaser(1);


    public static void main(String[] args) throws InterruptedException
    {

        ConditionSample sample = new ConditionSample();

        new Thread(sample.new Coder()).start();

        new Thread(sample.new Manager()).start();

        new Thread(sample.new TeamLeader()).start();

        TimeUnit.SECONDS.sleep(4);
        log.info("创建boss对象");

        Worker boss = sample.new Boss();
        new Thread(boss).start();

        log.info("等待所有线程");

        sample.phaser.arriveAndAwaitAdvance();

        log.info("全部结束");

    }


    private interface Work
    {
        void doWork();
    }


    private abstract class Worker implements Work, Runnable
    {

        public Worker()
        {
            phaser.register();
        }


        @Override
        public void doWork()
        {
            log.info("{}，正在工作...", Thread.currentThread().getName());
        }


        @Override
        public void run()
        {
            doWork();

            phaser.arriveAndDeregister();
        }
    }


    private class Boss extends Worker
    {
        @Override
        public void doWork()
        {
            try
            {
                lock.lock();
                log.info("我是大bos，我要给经理安排一个任务，任务安排中....");

                TimeUnit.SECONDS.sleep(5);

                log.info("我是大bos，任务安排完成");

                //唤醒manager线程
                managerCondition.signal();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }
    }


    private class Manager extends Worker
    {
        @Override
        public void doWork()
        {
            try
            {
                lock.lock();
                managerCondition.await();

                log.info("我是经理，大boss给我安排了一个任务，我要吩咐下面的leader完成");

                TimeUnit.SECONDS.sleep(3);

                log.info("我是经理，大boss给我安排了一个任务，我已经吩咐好了下面的leader完成");

                teamLeaderCondition.signal();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }
    }


    private class TeamLeader extends Worker
    {
        @Override
        public void doWork()
        {
            try
            {
                lock.lock();

                teamLeaderCondition.await();
                log.info("我是leader，上头的任务我要指导码农一起完成");

                TimeUnit.SECONDS.sleep(2);

                coderCondition.signal();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }
    }


    private class Coder extends Worker
    {

        @Override
        public void doWork()
        {
            try
            {
                lock.lock();
                coderCondition.await();
                log.info("我是码农，项目经理给我安排的开发任务");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }
    }
}
