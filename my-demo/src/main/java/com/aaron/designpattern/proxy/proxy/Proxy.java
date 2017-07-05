package com.aaron.designpattern.proxy.proxy;

public class Proxy implements Sourceable
{

    private Source source;


    public Proxy()
    {
        super();
        this.source = new Source();
    }


    @Override
    public void decoration()
    {

        before();
        source.decoration();
        after();
    }


    private void after()
    {
        System.out.println("after proxy");

    }


    private void before()
    {
        System.out.println("before proxy");

    }

}
