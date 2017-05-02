package com.aaron.designpattern.flyweigh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ������Ԫģʽ������ݿ����ӳ�
 *
 * @author Genius
 */
public class TestFlyWeight extends Thread
{

    ConnectionPool cp = null;


    @Override
    public void run()
    {

        try
        {
            cp = ConnectionPool.getConnectionPool();
            Thread.sleep(1000);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 20; i++)
        {
            Connection conn = cp.getConnection();
            try
            {
                System.out.println("�߳�" + name + "�õ��ĵ�" + (1 + i) + "�����ӣ��Ƿ�رգ�" + conn.isClosed() + "  ����" + conn.getMetaData()
                        .getDatabaseProductName());
            }
            catch (SQLException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }


    String name;


    public TestFlyWeight(String name)
    {
        this.name = name;
    }


    public static void main(String[] args) throws InterruptedException
    {
        TestFlyWeight t1 = new TestFlyWeight("A");
        TestFlyWeight t2 = new TestFlyWeight("B");

        t1.start();
        t2.start();
    }

}
