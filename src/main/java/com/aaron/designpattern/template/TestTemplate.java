package com.aaron.designpattern.template;

public class TestTemplate
{

    public static void main(String[] args)
    {
        String expression = "12+13";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(expression, "\\+");
        System.out.println(result);
    }
}
