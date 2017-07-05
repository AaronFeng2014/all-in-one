package com.aaron.designpattern.bridge;

import org.junit.Test;

public class TestBridge
{

    @Test
    public void m01()
    {
        Bridge b = new MyBridge();
        Sourceable s1 = new SourceableSub1();
        b.setSourceable(s1);
        b.decoration();

        Sourceable s2 = new SourceableSub2();
        b.setSourceable(s2);
        b.decoration();
    }
}
