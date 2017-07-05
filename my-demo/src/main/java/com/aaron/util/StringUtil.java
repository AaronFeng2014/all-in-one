package com.aaron.util;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-02-27
 */
public final class StringUtil
{
    private StringUtil()
    {
    }


    public static boolean isNotEmpty(String sourceString)
    {
        return sourceString != null && sourceString.length() > 0;
    }


    public static boolean isEmpty(String sourceString)
    {
        return !isEmpty(sourceString);
    }

}
