package com.aaron.designpattern.observe.imp1;

public class Observer1Impl implements Observer
{

    @Override
    public void update()
    {
        System.out.println("Observer1Impl has received");
    }

}
