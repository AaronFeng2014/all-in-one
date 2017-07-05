package com.aaron.util.codestatistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-06-18
 */
public class AnalyzeThread implements Runnable
{
    private static final Log LOG = LogFactory.getLog(CodeStatisticsUtil.class);

    private List<File> list;
    private static StatisticsResult result;


    public AnalyzeThread(List<File> list, StatisticsResult result)
    {
        this.list = list;
        this.result = result;
    }


    public void run()
    {
        statistics(list);
    }


    public static void statistics(List<File> list)
    {
        if (CollectionUtils.isEmpty(list))
        {
            return;
        }

        for (File file : list)
        {
            BufferedReader bufferedReader = null;
            try
            {
                bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    result.getTotalCodeLines().getAndIncrement();

                    if (line.trim().startsWith("/") || line.trim().startsWith("*"))
                    {
                        result.getCommentLines().getAndIncrement();
                    }
                    else
                    {
                        result.getValidCodeLines().getAndIncrement();

                        if (!line.trim().startsWith("import") && !line.trim().startsWith("package"))
                        {
                            result.getValidCodeLinesWithoutImport().getAndIncrement();
                            if (!"".equals(line))
                            {
                                result.getValidCodeLinesWithoutBlank().getAndIncrement();
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                LOG.error("Buffered 异常", e);
            }
            finally
            {
                try
                {
                    if (bufferedReader != null)
                    {
                        bufferedReader.close();
                    }
                }
                catch (IOException e)
                {
                    LOG.error("Buffered 关闭异常", e);
                }
            }
        }
    }

}
