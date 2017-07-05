package com.aaron.designpattern.observe.imp1;

public interface Subject
{

    public void add(Observer observer);

    public void del(Observer observer);

    public void notifyObservers();

    public void operation();
}
