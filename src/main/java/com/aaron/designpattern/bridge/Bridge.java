package com.aaron.designpattern.bridge;

public abstract class Bridge
{

    private Sourceable sourceable;


    public void setSourceable(Sourceable sourceable)
    {
        this.sourceable = sourceable;
    }


    public Sourceable getSourceable()
    {
        return sourceable;
    }


    public void decoration()
    {
        sourceable.decoration();
    }
}
