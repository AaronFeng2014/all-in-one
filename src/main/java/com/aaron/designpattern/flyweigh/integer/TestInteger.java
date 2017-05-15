/**
 *
 */
package com.aaron.designpattern.flyweigh.integer;

/**
 * @author Aaron
 */
public class TestInteger
{

    public static void main(String[] args)
    {
        Integer i1 = 444;
        Integer i2 = 444;
        /**
         * ��i��-128 �� 127֮�䣬����i1��i2����ȵģ�����ʹ�õ���Ԫ�����ģʽ
         */
        System.out.println(i1 == i2);
    }
}
