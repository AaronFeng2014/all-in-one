package com.aaron.rxjava;

import io.reactivex.Flowable;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/6/25
 */
public class HelloWorld
{
    public static void main(String[] args)
    {
        Flowable.just("hello world").subscribe(System.out::println);
    }
}
