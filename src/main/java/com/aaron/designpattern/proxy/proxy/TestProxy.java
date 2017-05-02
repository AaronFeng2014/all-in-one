package com.aaron.designpattern.proxy.proxy;

import org.junit.Test;

public class TestProxy
{
    @Test
    public void m01()
    {
        Sourceable s = new Proxy();
        s.decoration();
    }
}
