package com.aaron.designpattern.facade;

public class Memory implements AA
{

    @Override
    public void startUp()
    {
        System.out.println("Memory StartUp");

    }


    @Override
    public void shutDown()
    {
        System.out.println("Memory shutDown");

    }

}
