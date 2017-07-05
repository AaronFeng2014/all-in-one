package com.aaron.designpattern.strategy;

public class Plus extends AbstractCalculator implements ICalculator
{

    @Override
    public int calculate(String expression)
    {
        int[] arrayInt = split(expression, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}
