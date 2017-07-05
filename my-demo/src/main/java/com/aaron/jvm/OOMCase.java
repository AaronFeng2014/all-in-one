package com.aaron.jvm;

import com.aaron.jvm.entity.OOMEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存异常常见错误
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
public class OOMCase
{
    private static List<OOMEntity> list = new ArrayList<>();

    private static int times = 0;


    /**
     * 模拟堆内存异常
     * <p>
     * 可能还会诱发下列异常
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * 上述异常产生原因：FGC台频繁，且回收的空间非常低，即FGC基本在做无用功，垂死挣扎
     * <p>
     * <p>
     * 虚拟机参数：-Xms10m -Xmx10m
     */
    public static void oomOnHeap()
    {
        int index = 0;
        while (true)
        {

            System.out.println(++index);

            //每次放1Mb数据到list中
            list.add(new OOMEntity());
        }
    }


    /**
     * 模拟栈内存溢出
     * <p>
     * 虚拟机参数：-Xss128k，指定栈空间大小为128kb
     * <p>
     * 栈的基本数据接口是栈帧，方法调用开始创建一个栈帧，方法结束时，销毁一个栈帧
     * <p>
     * 该实例一直递归调用自己，使一直创建栈帧，而不销毁栈帧，最后导致栈空间不足，从而栈空间溢出
     */
    public static void oomOnStack()
    {
        System.out.println("调用次数：" + (++times));

        oomOnStack();
    }
}
