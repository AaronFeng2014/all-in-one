package com.aaron.util.codestatistics;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-06-18
 */
public final class CodeStatisticsUtil
{
    private static final Log LOG = LogFactory.getLog(CodeStatisticsUtil.class);
    private static final String DEFAULT_PATH = "D:\\Code\\delivery";

    private static StatisticsResult result = new StatisticsResult();

    private Semaphore semaphore = new Semaphore(20);

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    private static List<File> list = new ArrayList<>();


    private CodeStatisticsUtil()
    {
    }


    public static StatisticsResult analyze()
    {
        return analyze(DEFAULT_PATH);
    }


    public static StatisticsResult analyze(String codePath)
    {
        File file = new File(codePath);

        if (!file.exists())
        {
            LOG.warn("file is not exist, please check!");
            return result;
        }

        addFileToList(file);

        if (list.size() > 0)
        {
            new AnalyzeThread(null, result);
            AnalyzeThread.statistics(list);
        }

        return result;
    }


    public static StatisticsResult multiThreadAnalyzeFile(String codePath)
    {
        File file = new File(codePath);

        if (!file.exists())
        {
            LOG.warn("file is not exist, please check!");
            return result;
        }

        addFileToList(file);

        if (list.size() > 0)
        {
            EXECUTOR_SERVICE.execute(new AnalyzeThread(list, result));
        }

        return result;
    }


    private static void addFileToList(final File file)
    {
        if (file == null)
        {
            return;
        }

        if (file.isDirectory())
        {
            final File[] files = file.listFiles();
            if (files == null)
            {
                return;
            }

            for (final File f : files)
            {
                if (f.isDirectory())
                {
                    addFileToList(f);
                }
                else
                {
                    multiThreadAnalyzeFile(f);
                }
            }
        }
        else
        {
            multiThreadAnalyzeFile(file);
        }
    }


    private static void multiThreadAnalyzeFile(File file)
    {
        list.add(file);

        if (list.size() == 50)
        {
            EXECUTOR_SERVICE.execute(new AnalyzeThread(list, result));
            list = new ArrayList<>();
        }

    }

}
