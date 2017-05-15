package com.aaron.designpattern.observe.imp1;

/**
 * Subject接口，有add，del，update，notifyObserve，operation方法
 * 抽象类AbstractSubject实现Subject接口 （抽象类实现接口，可以不全部实现接口里面的抽象方法），并在列里面重写了subject里面的方法
 * MySubject继承抽象类AbstractSubject，重写operation方法
 * Observe接口里面有一个抽象方法update（）；
 * Observer1Impl和Observer2Impl实现了Observe接口，并重写了里面的update方法
 *
 * @author FENG
 */
public class TestObserver
{

    public static void main(String[] args)
    {

        Subject sub = new MySubject();
        sub.add(new Observer1Impl());
        sub.add(new Observer2Impl());
        sub.operation();
    }
}
