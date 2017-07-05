package com.aaron.designpattern.observe.imp1;

import java.util.Vector;

public abstract class AbstractSubject implements Subject
{

    private Vector<Observer> vector = new Vector<Observer>();


    @Override
    public void add(Observer observer)
    {

        vector.add(observer);
    }


    @Override
    public void del(Observer observer)
    {

        vector.remove(observer);
    }


    @Override
    public void notifyObservers()
    {

        for (Observer o : vector)
        {
            o.update();
        }
    }
}
