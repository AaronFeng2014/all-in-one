package com.aaron.designpattern.facade;

public class CPU implements AA
{

    public void startUp()
    {
        System.out.println("CPU StartUp");
    }


    public void shutDown()
    {
        System.out.println("CPU ShutDown");
    }

}
