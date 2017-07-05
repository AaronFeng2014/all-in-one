package com.aaron.util.codestatistics;

import org.junit.Test;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-02-27
 */
public class CodeStatisticsUtilTest
{
    @Test
    public void analyze() throws Exception
    {
        long begin = System.currentTimeMillis();
        StatisticsResult statisticsResult = CodeStatisticsUtil.multiThreadAnalyzeFile("D:\\Code\\dev\\delivery\\hotel-delivery-common");

        CodeStatisticsUtil.EXECUTOR_SERVICE.shutdown();

        while (true)
        {
            if (CodeStatisticsUtil.EXECUTOR_SERVICE.isTerminated())
            {
                System.out.println("线程池已结束");
                break;
            }
        }

        System.out.println(statisticsResult);
        System.out.println("总共耗时：" + (System.currentTimeMillis() - begin) + "ms");
    }
}