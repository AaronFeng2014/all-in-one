package com.aaron.designpattern.template;

/**
 * 辅助类
 *
 * @author FENG
 */
public abstract class AbstractCalculator
{

    public int[] split(String expression, String opt)
    {
        String[] array = expression.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }


    public int calculate(String expression, String opt)
    {
        int[] array = split(expression, opt);
        return calculate(array[0], array[1]);
    }


    public abstract int calculate(int num1, int num2);

}
