package com.aaron.util.keywordsearch;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchKeyWords
{
    private static String defaultSearchPath = "D:\\Code";
    private static String keyWords = "";
    private static List<String> fileExtensionList = new ArrayList<String>();

    private static List<File> list = new ArrayList<File>();


    /**
     * 在指定的目录中搜索匹配的内容，默认搜索路径D:\Code，默认搜索properties,xml,txt,java文件
     *
     * @param keyWord String：要搜索的关键字
     */
    public static void search(String keyWord)
    {
        if (keyWord == null || "".equals(keyWord))
        {
            throw new RuntimeException("参数异常");
        }
        else
        {
            keyWords = keyWord;
            File file = new File(defaultSearchPath);

            if (!file.exists())
            {
                try
                {
                    throw new Exception("文件夹或文件不存在," + file.getAbsolutePath());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            doProcess(file);

            if (!list.isEmpty())
            {
                new Thread(new SearchThread(list, keyWords, fileExtensionList)).start();
                list = new ArrayList<>();
            }
        }
    }


    private static void doProcess(File file)
    {
        if (file == null)
        {
            return;
        }
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (File f : files)
            {
                if (f.isDirectory())
                {
                    doProcess(f);
                }
                else
                {
                    findMatchContent(f);
                }
            }
        }
        else
        {
            findMatchContent(file);
        }
    }


    private static void findMatchContent(File file)
    {

        list.add(file);
        if (list.size() % 1000 == 0)
        {
            new Thread(new SearchThread(list, keyWords, fileExtensionList)).start();
            list = new ArrayList<File>();
        }
    }


    /**
     * 在指定的目录中，指定文件中搜索匹配的内容
     *
     * @param keyWord String:要搜索的关键字
     * @param path String:要搜索的路径
     */
    public static void search(String keyWord, String path)
    {
        if (keyWord == null || "".equals(keyWord))
        {
            throw new RuntimeException("参数异常");
        }

        defaultSearchPath = path;

        search(keyWord);
    }


    /**
     * 在指定的目录中，指定文件中搜索匹配的内容
     *
     * @param keyWord String:要搜索的关键字
     * @param path String:要搜索的路径
     * @param fileExtensions String[]:要搜索的文件后缀名
     */
    public static void search(String keyWord, String path, String[] fileExtensions)
    {
        if (fileExtensions == null || fileExtensions.length == 0)
        {
            throw new RuntimeException("参数异常,fileExtensions不能为空");
        }

        fileExtensionList = Arrays.asList(fileExtensions);

        search(keyWord, path);
    }


    /**
     * 在指定的目录中，指定文件中搜索匹配的内容
     *
     * @param keyWord String:要搜索的关键字
     * @param fileExtensions String[]:要搜索的文件后缀名
     */
    public static void search(String keyWord, String[] fileExtensions)
    {
        if (fileExtensions == null || fileExtensions.length == 0)
        {
            throw new RuntimeException("参数异常,fileExtensions不能为空");
        }

        fileExtensionList = Arrays.asList(fileExtensions);

        search(keyWord);
    }
}
