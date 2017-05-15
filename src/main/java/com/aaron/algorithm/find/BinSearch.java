package com.aaron.algorithm.find;

import java.util.Collection;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-02
 */
public class BinSearch
{
    public static <T> T searchTarget(Collection<T> collection, T target)
    {
        int beginIndex = 0;
        int endIndex = collection.size();
        int halfIndex = (beginIndex + endIndex) / 2;

        T[] tArray = (T[])new Object[collection.size()];

        T[] array = collection.toArray(tArray);

        T halfValue = array[halfIndex];

        while (halfIndex != beginIndex)
        {
            //if (target)
        }

        return halfValue;
    }
}
