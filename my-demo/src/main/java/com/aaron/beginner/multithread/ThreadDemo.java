package com.aaron.beginner.multithread;

import javax.persistence.Id;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-11-11
 */
public class ThreadDemo
{

    private String content = "rer";


    public static void main(String[] args)
    {

        ThreadDemo demo = new ThreadDemo();
        for (int i = 0; i < 10; i++)
        {
            new Thread(() -> demo.testStringLock(demo)).start();
        }
    }


    private void testStringLock(ThreadDemo content)
    {
        if (StringUtils.isEmpty(content.content))
        {
            return;
        }

        System.out.println(Thread.currentThread().getName() + "--参数是--" + content);
        synchronized (content.content)
        {
            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "--运行结束--参数是--" + content);
    }
}
