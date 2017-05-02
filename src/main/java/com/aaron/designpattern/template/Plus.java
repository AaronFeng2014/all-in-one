package com.aaron.designpattern.template;

public class Plus extends AbstractCalculator
{

    @Override
    public int calculate(int num1, int num2)
    {
        return num1 + num2;
    }

}
