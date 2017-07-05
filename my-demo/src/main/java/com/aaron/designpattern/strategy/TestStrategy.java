package com.aaron.designpattern.strategy;

public class TestStrategy
{

    public static void main(String[] args)
    {
        String expression = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(expression);
        System.out.println(result);
    }
}
