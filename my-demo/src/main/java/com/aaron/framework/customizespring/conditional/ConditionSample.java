package com.aaron.framework.customizespring.conditional;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
public class ConditionSample
{

    public static void main(String[] args) throws IOException
    {
        String packages = "com.aaron.framework.customizespring.conditional";

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(packages);

        FileListService bean = context.getBean(FileListService.class);

        String command = bean.getCommand();

        System.out.println(command);

        InputStream inputStream = Runtime.getRuntime().exec(command).getInputStream();

        System.out.println(IOUtils.toString(inputStream));
    }
}
