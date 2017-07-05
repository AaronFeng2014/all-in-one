package com.aaron.designpattern.bridge;

public class MyBridge extends Bridge
{

    public void decoration()
    {
        getSourceable().decoration();
    }
}
