package com.aaron.designpattern.adapter.classAdapter;

public class Wrapper implements Targetable
{

    private Source source;


    public Wrapper(Source source)
    {
        this.source = source;
    }


    @Override
    public void source()
    {
        source.source();

    }


    @Override
    public void newSource()
    {
        System.out.println("this is new method in interface!");
    }

}
