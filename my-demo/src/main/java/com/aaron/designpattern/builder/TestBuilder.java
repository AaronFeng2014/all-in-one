package com.aaron.designpattern.builder;

import org.junit.Test;

import java.util.List;

public class TestBuilder
{

    @Test
    public void m01()
    {
        Builder builder = new Builder();
        List<Sender> list = builder.producerSenderSNS(10000);
        for (Sender s : list)
        {
            s.sender();
        }

    }

}
