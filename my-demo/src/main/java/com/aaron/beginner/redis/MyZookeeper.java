package com.aaron.beginner.redis;

import java.net.InetAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author FengHaixin
 * @description zookeeper自学测试
 * @date 2016-06-02
 */
public class MyZookeeper
{
    private static final Log LOG = LogFactory.getLog(MyZookeeper.class);
    private static final String URL = "192.168.2.175:12181";
    private static final String LISTENER_PATH = "/delivery/Hub/init";

    private CuratorFramework factory;


    /**
     *
     */
    @Before
    public void init() throws Exception
    {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        factory = CuratorFrameworkFactory.newClient(URL, retryPolicy);

        factory.start();

        setTreeListener(factory);
    }


    @Test
    public void testListener() throws Exception
    {
        Thread.currentThread().setName("监听");
        int index = 0;
        while (true)
        {
            //factory.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/delivery/Hub/init/app", ("test-" + InetAddress.getLocalHost().getHostAddress()).getBytes("UTF-8"));
            factory.setData().forPath("/delivery/Hub/init",InetAddress.getLocalHost().getHostAddress().getBytes("UTF-8"));
            Thread.sleep(5000);
            if (++index == 100)
            {
                System.out.println("shutdown");
                break;
            }
        }
    }


    /**
     * 创建一个目录节点,并返回该节点的全局唯一标示符
     * 参数说明：
     * CreateMode.PERSISTENT：标识该节点是持久化节点，session失效后，该节点也不会消失
     * CreateMode.EPHEMERAL：标识该节点是临时节点，session失效后，该节点会消失
     * CreateMode.EPHEMERAL_SEQUENTIAL：标识该节点是临时且自动编号的节点
     * CreateMode.PERSISTENT_SEQUENTIAL：标识该节点是顺序且自动编号的目录节点
     */
    @Test
    public void testCreateNode() throws Exception
    {
        //factory.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/zkTest/curator", "test".getBytes("UTF-8"));

    }


    @Test
    public void testModifyNode() throws Exception
    {

        Thread thread = new Thread(() ->{
            try
            {
                for (int i = 0; i < 50; i++)
                {
                    factory.setData().forPath("/zkTest/curator", ("test" + i).getBytes("UTF-8"));
                    System.out.println("第" + i + "次修改");
                    Thread.sleep(2000);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }, "zk数据修改线程");
        thread.start();

        while (true)
        {
            Thread.sleep(1000);
            if (!thread.isAlive())
            {
                System.out.println("shutdown");
                break;
            }
        }
    }


    /**
     *
     */
    @After
    public void close()
    {
        factory.close();

    }


    private void setTreeListener(CuratorFramework client) throws Exception
    {
        TreeCache cache = new TreeCache(client, LISTENER_PATH);

        cache.getListenable().addListener((srcClient, event) -> {
            TreeCacheEvent.Type type = event.getType();

            switch (type)
            {
                case NODE_ADDED:
                    System.out.println("NODE_ADDED");
                    break;
                case NODE_REMOVED:
                    System.out.println("NODE_REMOVED");
                    break;
                case NODE_UPDATED:
                    System.out.println(
                            Thread.currentThread().getName() + "---" + "NODE_UPDATED,new data:" + new String(event.getData().getData(),
                                    "utf-8"));
                    break;
                case INITIALIZED:
                    System.out.println(type);
                    break;
                case CONNECTION_SUSPENDED:
                    System.out.println(type);
                    break;
                case CONNECTION_LOST:
                    System.out.println(type);
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println(type);
                    break;
                default:
                    System.out.println("unknown");
                    break;
            }
        });

        cache.start();
    }


    private void setListener(CuratorFramework client) throws Exception
    {
        PathChildrenCache childrenCache = new PathChildrenCache(client, "/zkTest/curator", true);

        childrenCache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) -> System.out.println("---++++++++++++====="));

        System.out.println("Register zk watcher successfully!");

        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
    }

}
