package com.aaron.beginner.redis;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author FengHaixin
 * @description zookeeper节点状态监听，当节点的状态发生了改变后，会调用该类的process方法
 * @date 2016-06-02
 */
public class MyWatcher implements Watcher
{

    public void process(WatchedEvent watchedEvent)
    {
        System.out.println("已触发了" + watchedEvent.getType().name() + "事件");
    }
}
