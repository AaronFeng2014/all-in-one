package com.aaron.designpattern.singleton;

import junit.framework.TestCase;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
public class SingleTonInstanceTest extends TestCase
{
    public void testGetInstance() throws Exception
    {
        SingleTonInstance instance1 = SingleTonInstance.getInstance();
        instance1.setName("name_1");


        SingleTonInstance instance2 = SingleTonInstance.getInstance();
        instance1.setName("name_2");

        SingleTonInstance instance3 = SingleTonInstance.getInstance();
        instance1.setName("name_3");

        SingleTonInstance instance4 = SingleTonInstance.getInstance();
        instance1.setName("name_4");

        System.out.println(instance1 == instance2);
        System.out.println(instance3 == instance2);
        System.out.println(instance3 == instance4);
        System.out.println(instance1 == instance4);
    }

}