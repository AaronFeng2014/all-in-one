package com.aaron.util.file;

import com.aaron.util.StringUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author FengHaixin
 * @description 对文件的相关操作，查找文件中的具体内容
 * @date 2016-05-23
 */
public final class FileUtils
{
    private static final Log LOG_RECORD = LogFactory.getLog(FileUtils.class);
    private static final int FILE_SIZE = 100;
    private static final int THREAD_CONUT = 10;

    private static String defaultSearchPath = "D:\\Code";
    private static String keyWords = "";
    private static List<String> fileExtensionList = Arrays.asList(new String[] {"properties", "xml", "txt", "java"});
    private static List<File> list = new ArrayList<File>();
    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_CONUT);

    public static int ALIVE_THREAD;


    private FileUtils()
    {
    }


    /**
     * 在指定的目录中搜索匹配的内容，默认搜索路径D:\Code，默认搜索properties,xml,txt,java文件
     *
     * @param keyWord String：要搜索的关键字
     */
    public static void searchContents(final String keyWord)
    {
        if (keyWord == null || "".equals(keyWord))
        {
            LOG_RECORD.error("parameter keyWord is null");
            return;
        }

        keyWords = keyWord;
        final File file = new File(defaultSearchPath);

        if (!file.exists())
        {
            LOG_RECORD.error("folder is not exist：" + file.getAbsolutePath());
            return;
        }
        doProcess(file);

        if (!list.isEmpty())
        {
            ALIVE_THREAD++;
            new Thread(new SearchThread(list)).start();
            //EXECUTOR_SERVICE.execute(new SearchThread(list));
            list = new ArrayList<>();
        }
    }


    private static void doProcess(final File file)
    {
        if (file == null || file.isHidden())
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


    private static void findMatchContent(final File file)
    {
        list.add(file);
        if (list.size() % FILE_SIZE == 0)
        {
            ALIVE_THREAD++;
            new Thread(new SearchThread(list)).start();
            //EXECUTOR_SERVICE.execute(new SearchThread(list));
            list = new ArrayList<File>();
        }
    }


    /**
     * 在指定的目录中，指定文件中搜索匹配的内容，默认搜索路径D:\Code，默认搜索properties,xml,txt,java文件
     *
     * @param keyWord String:要搜索的关键字
     * @param path String:要搜索的路径
     */
    public static void searchContents(final String keyWord, final String path)
    {
        if (keyWord == null || "".equals(keyWord))
        {
            LOG_RECORD.error("parameter keyWord is null， keyWord=" + keyWord);
            return;
        }

        defaultSearchPath = path;

        searchContents(keyWord);
    }


    /**
     * 在指定的目录中，指定文件中搜索匹配的内容，默认搜索路径D:\Code，默认搜索properties,xml,txt,java文件
     *
     * @param keyWord String:要搜索的关键字
     * @param path String:要搜索的路径
     * @param fileExtensions String[]:要搜索的文件后缀名
     */
    public static void search(final String keyWord, final String path, final String[] fileExtensions)
    {
        if (fileExtensions == null || fileExtensions.length == 0)
        {
            LOG_RECORD.error("parameter fileExtensions is null");
            return;
        }

        fileExtensionList = Arrays.asList(fileExtensions);

        searchContents(keyWord, path);
    }


    /**
     * 在指定的目录中，指定文件中搜索匹配的内容，默认搜索路径D:\Code，默认搜索properties,xml,txt,java文件
     *
     * @param keyWord String:要搜索的关键字
     * @param fileExtensions String[]:要搜索的文件后缀名
     */
    public static void search(final String keyWord, final String[] fileExtensions)
    {
        if (fileExtensions == null || fileExtensions.length == 0)
        {
            LOG_RECORD.error("parameter fileExtensions is null");
            return;
        }

        fileExtensionList = Arrays.asList(fileExtensions);

        searchContents(keyWord);
    }


    private static final class SearchThread implements Runnable
    {

        private final List<File> list;


        private SearchThread(final List<File> list)
        {
            this.list = list;
        }


        public void run()
        {

            for (final File file : list)
            {
                final int indexOf = file.getName().lastIndexOf(".");
                final String extension = file.getName().substring(indexOf + 1);
                if (fileExtensionList.contains(extension))
                {
                    try
                    {
                        matchContent(file);
                    }
                    catch (IOException e)
                    {
                        LOG_RECORD.error("find content error", e);
                    }
                }
            }
            ALIVE_THREAD--;
        }


        private void matchContent(final File file) throws IOException
        {
            BufferedReader bufferedReader = null;
            try
            {
                bufferedReader = new BufferedReader(new FileReader(file));
                String read;
                int lineCount = 0;
                while ((read = bufferedReader.readLine()) != null)
                {
                    lineCount++;
                    if (read.contains(keyWords))
                    {
                        LOG_RECORD.info("内容匹配成功：位于第" + lineCount + "行，路径为：" + file.getAbsoluteFile());
                    }
                }
            }
            catch (Exception e)
            {
                LOG_RECORD.error("open stream bufferedReader failed!", e);
            }
            finally
            {
                closeResource(bufferedReader);
            }

        }


        private void closeResource(final BufferedReader bufferedReader) throws IOException
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    LOG_RECORD.error("close stream bufferedReader failed!", e);
                }
            }
        }


        /**
         * 根据指定的文件名获取文件的扩展名
         *
         * @param fileName String：文件全路径
         * @return String：文件的扩展名
         */
        public static String getFileExtension(String fileName)
        {
            if (StringUtil.isEmpty(fileName))
            {
                throw new RuntimeException("文件名错误");
            }

            File file = new File(fileName);

            if (!file.isFile())
            {
                throw new RuntimeException("不是一个文件");
            }

            String name = file.getName();
            if (name.lastIndexOf(".") != -1)
            {
                return name.substring(name.lastIndexOf(".") + 1);
            }

            return "";
        }
    }


    public static void main(String[] args)
    {
        String path = "D:\\Code\\GitHub\\BigData-Scala\\spark-warehouse";

        FileUtils.searchContents("直连专用", path);
    }

}