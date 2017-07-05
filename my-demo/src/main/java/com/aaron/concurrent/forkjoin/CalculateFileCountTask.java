package com.aaron.concurrent.forkjoin;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-03
 */
public class CalculateFileCountTask extends RecursiveTask<Integer>
{
    private String directoryPath;


    public CalculateFileCountTask(String directoryPath)
    {
        this.directoryPath = directoryPath;
    }


    @Override
    protected Integer compute()
    {
        int count = 0;
        File file = new File(directoryPath);
        List<File> fileList = new ArrayList<>();
        forkTask(file, fileList);

        for (int i = 0; i < fileList.size(); i++)
        {
            List<File> files = fileList.subList(i, (i + 1) * 1000);
        }
        return null;
    }


    private void forkTask(File file, List<File> fileList)
    {
        if (file.isDirectory())
        {
            fileList.add(file);

            File[] files = file.listFiles();
            if (files == null)
            {
                return;
            }

            for (File f : files)
            {
                if (f.isDirectory())
                {
                    forkTask(f, fileList);
                }
            }
        }
    }
}
