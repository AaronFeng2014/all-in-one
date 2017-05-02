package com.aaron.designpattern.decoration;

public class Decoration implements Sourceable
{

    private Source source;


    public Decoration(Source source)
    {
        super();
        this.source = source;
    }


    @Override
    public void decoration()
    {
        System.out.println("before decoreation");
        source.decoration();
        System.out.println("after decoration");
    }

}
