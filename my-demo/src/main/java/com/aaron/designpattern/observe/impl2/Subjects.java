/**
 *
 */
package com.aaron.designpattern.observe.impl2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron
 */
public abstract class Subjects
{

    List<Observer> list = new ArrayList<Observer>();


    public void addObsever(Observer observer)
    {
        list.add(observer);
    }


    public void removeObsever(Observer observer)
    {
        if (list.contains(observer))
        {
            list.remove(observer);
        }
    }


    public void notifyObsever(Subjects sub, int newAge)
    {
        for (Observer observer : list)
        {
            if (sub != observer)
                observer.update(newAge);
        }
    }


    abstract void change(int newAge);
}
