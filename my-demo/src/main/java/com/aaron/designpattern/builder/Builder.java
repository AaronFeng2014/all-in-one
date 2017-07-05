package com.aaron.designpattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * 工厂类模式提供的是创建单个类的模式，
 * 而建造者模式则是将各种产品集中起来进行管理，用来创建复合对象， 所谓复合对象就是指某个类具有不同的属性，
 */
public class Builder
{

    public List<Sender> list = new ArrayList<Sender>();


    public List<Sender> producerSenderSNS(int count)
    {
        for (int i = 0; i < count; i++)
        {
            list.add(new SenderSNS());
        }
        return list;
    }


    public List<Sender> producerSenderSMS(int count)
    {
        for (int i = 0; i < count; i++)
        {
            list.add(new SendSMS());
        }
        return list;
    }


    public List<Sender> producerSenderMail(int count)
    {
        for (int i = 0; i < count; i++)
        {
            list.add(new SendMail());
        }
        return list;
    }
}
