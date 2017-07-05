package com.aaron.designpattern.builder;

public class SendSMS implements Sender
{

    @Override
    public void sender()
    {
        System.out.println("发信息");

    }

}
