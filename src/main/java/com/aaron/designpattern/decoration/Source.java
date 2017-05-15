package com.aaron.designpattern.decoration;

/**
 * @author Genius
 */
public class Source implements Sourceable
{

    @Override
    public void decoration()
    {
        System.out.println("这是源方法");

    }

}
