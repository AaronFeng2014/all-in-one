package com.aaron.designpattern.strategy;

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
}
