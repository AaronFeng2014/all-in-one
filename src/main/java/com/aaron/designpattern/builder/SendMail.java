package com.aaron.designpattern.builder;

public class SendMail implements Sender
{

    @Override
    public void sender()
    {
        System.out.println("发邮件");
    }

}
