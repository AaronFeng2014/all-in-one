package com.aaron.designpattern.state;

import junit.framework.TestCase;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
public class DogContextTest extends TestCase
{
    public void testAction() throws Exception
    {

        DogContext context = new DogContext();

        System.out.println(context.getDogState());

        context.action();

        context.setDogState(context.getBadDog());

        context.action();
    }

}