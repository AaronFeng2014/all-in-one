package com.aaron.designpattern.bridge;

public class SourceableSub1 implements Sourceable
{

    @Override
    public void decoration()
    {
        System.out.println("first");

    }

}
