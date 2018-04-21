package com.aaron.algorithm;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 快速排序
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-11-14
 */
public class QuictSourtDemo<T extends Comparable<T>>
{
    public void sort(T[] t)
    {
        if (ArrayUtils.isEmpty(t))
        {
            throw new NullPointerException("参数不能为空");
        }

        List<T> tList = Arrays.asList(t);

        T baseElement = tList.get(0);

        List<T> left = new ArrayList<>();

        List<T> right = new ArrayList<>();

        while (left.size() != 1 && right.size() != 1)
        {
            //compareLeft();
            sort(baseElement, tList.subList(1, tList.size()), left, right);

        }
    }


    private void sort(T baseElement, List<T> sourceList, List<T> left, List<T> right)
    {

        for (T t : sourceList)
        {
            if (baseElement.compareTo(t) < 0)
            {
                left.add(baseElement);
            }
            else
            {
                right.add(baseElement);
            }
        }
    }


    private void compareLeft(T[] left)
    {
    }


    private void compareRight(T[] right)
    {
    }
}
