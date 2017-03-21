package com.aaron.util.keywordsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aaron on 2016-05-04.
 */
public class SearchThread implements Runnable
{
    private String keyWords;
    private List<File> list;
    private List<String> includeFileExtension = Arrays.asList(new String[] {"properties", "xml", "txt", "java"});


    public SearchThread(List<File> list, String keyWords, List<String> includeFileExtension)
    {
        this.keyWords = keyWords;
        this.list = list;
        if (!CollectionUtils.isEmpty(includeFileExtension))
        {
            this.includeFileExtension = includeFileExtension;
        }
    }


    public void run()
    {
        for (File file : list)
        {
            int indexOf = file.getName().lastIndexOf(".");
            String extension = file.getName().substring(indexOf + 1);
            if (includeFileExtension.contains(extension))
            {
                try
                {
                    match(file);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    private void match(File file) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String read;
        int index = 0;
        while ((read = bufferedReader.readLine()) != null)
        {
            index++;
            if (read.contains(keyWords))
            {
                System.out.println("内容匹配成功：位于第" + index + "行，路径为：" + file.getAbsoluteFile());
                break;
            }
        }
    }

}
