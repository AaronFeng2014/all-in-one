package com.aaron.designpattern.facade;

public class Disk implements AA
{

    @Override
    public void startUp()
    {
        System.out.println("Disk startUp");

    }


    @Override
    public void shutDown()
    {
        System.out.println("Disk shutDown");

    }

}
