package com.aaron.designpattern.singleton;

import com.aaron.jvm.OOMCase;
import com.aaron.jvm.entity.OOMEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 单例Double Check
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
public class SingleTonInstance
{
    @Setter
    @Getter
    private String name;

    private static volatile SingleTonInstance instance;


    private SingleTonInstance()
    {

    }


    public static SingleTonInstance getInstance()
    {
        if (instance != null)
        {
            return instance;
        }

        synchronized (SingleTonInstance.class)
        {
            if (instance == null)
            {
                instance = new SingleTonInstance();
            }

        }

        return instance;
    }


    public static void main(String[] args)
    {
        String a = "java";

        replace(a);

        System.out.println(a);

        OOMEntity entity = new OOMEntity();

        entity.setName("change----1");

        replace(entity);

        System.out.println(entity.toString());

    }


    private static void replace(String string)
    {
        string.replace("a", "A");
    }


    private static void replace(OOMEntity entity)
    {
        entity.setName("hehe");

        entity = new OOMEntity();

        entity.setName("change");

        System.out.println(entity);
    }

}
