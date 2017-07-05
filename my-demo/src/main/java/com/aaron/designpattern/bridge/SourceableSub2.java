package com.aaron.designpattern.bridge;

public class SourceableSub2 implements Sourceable
{

    @Override
    public void decoration()
    {
        System.out.println("second");
    }

}
