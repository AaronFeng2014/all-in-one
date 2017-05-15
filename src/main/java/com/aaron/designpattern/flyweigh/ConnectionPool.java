package com.aaron.designpattern.flyweigh;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool
{

    private Vector<Connection> pool;
    /**
     * ��������,���ݿ�������Ϣ
     */

    private String url;
    private String username;
    private String password;
    private String driverClassName;


    /**
     * ��ȡ���ݿ������ļ�����ȡ���е��û��������룬������url�ȡ�����
     *
     * @throws java.io.IOException
     */
    public void readProperties() throws IOException
    {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("dataSource_MySQL.properties");
        Properties p = new Properties();
        p.load(is);
        System.out.println("��ȡ�����ļ�");
        url = p.getProperty("dataSource.url");
        username = p.getProperty("dataSource.username");
        password = p.getProperty("dataSource.password");
        driverClassName = p.getProperty("dataSource.driverClassName");
    }


    // ���ݿ��ʼ��
    private int poolSize = 10;
    private static ConnectionPool instance = null;
    private Connection conn = null;


    /**
     * �˴���˽�й��췽�����ϣ�ʹ��singleton���ģʽ
     *
     * @return
     * @throws java.io.IOException
     */
    public static ConnectionPool getConnectionPool() throws IOException
    {
        if (instance == null)
        {
            synchronized (ConnectionPool.class)
            {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }


    // ���췽�������ڳ�ʼ��
    private ConnectionPool() throws IOException
    {
        readProperties();
        getNewConnection();
    }


    private void getNewConnection()
    {
        pool = new Vector<Connection>(poolSize);

        for (int i = 0; i < poolSize; i++)
        {
            try
            {
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    /* �������ӵ����ӳ� */
    public synchronized void relase(Connection conn)
    {
        pool.add(conn);
    }


    /* �õ����ӳ��е�һ�����ݿ����� */
    public synchronized Connection getConnection()
    {
        if (pool.size() > 0)
        {
            Connection conn = pool.get(0);
            pool.remove(conn);
        }
        else
        {
            System.out.println("���ӳ��������꣬�½�����");
            getNewConnection();
        }
        return conn;
    }

}
