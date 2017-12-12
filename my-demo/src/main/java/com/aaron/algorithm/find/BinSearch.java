package com.aaron.algorithm.find;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二分查找算法java实现
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-02
 */
public class BinSearch
{

    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<>();
        list.add(23);
        list.add(34);
        list.add(12);
        list.add(9);
        list.add(16);

        list.add(41);
        list.add(36);
        list.add(12);
        list.add(4);
        list.add(25);

        Collections.sort(list);
        System.out.println(list);
        for (Integer integer : list)
        {
            System.out.println(find(list, integer));
        }
    }


    public static <T extends Comparable<? super T>> int find(List<T> list, T target)
    {

        int startIndex = 0;
        int endIndex = list.size();

        while (startIndex <= endIndex)
        {
            int middleIndex = (startIndex + endIndex) / 2;

            T t = list.get(middleIndex);
            //待比较数据大大于查找数据
            if (t.compareTo(target) == 0)
            {
                return middleIndex;
            }

            if (t.compareTo(target) > 0)
            {
                endIndex = middleIndex - 1;
            }
            else
            {
                startIndex = middleIndex + 1;
            }
        }

        return -1;
    }
}
