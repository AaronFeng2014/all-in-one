package com.aaron.jvm;

import junit.framework.TestCase;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
public class OOMCaseTest extends TestCase
{
    public void testOomOnHeap() throws Exception
    {
        OOMCase.oomOnHeap();
    }


    public void testOomOnStack() throws Exception
    {
        OOMCase.oomOnStack();
    }

}