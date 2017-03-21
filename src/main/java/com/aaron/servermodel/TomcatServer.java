package com.aaron.servermodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tomcat原理模拟
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-11-28
 */
public class TomcatServer
{
    /**
     * 默认监听端口
     */
    private static final int DEFAULT_PORT = 8888;

    private ExecutorService service = Executors.newFixedThreadPool(20);


    public static void main(String[] args)
    {
        TomcatServer tomcatServer = new TomcatServer();
        try
        {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);

            while (true)
            {
                Socket socket = serverSocket.accept();

                Thread handleRequestThread = new Thread(tomcatServer.new HandleRequest(socket), "handleRequestThread");

                handleRequestThread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 请求处理Handle
     */
    private class HandleRequest implements Runnable
    {
        private Socket socket;


        HandleRequest(Socket socket)
        {
            this.socket = socket;
        }


        public void run()
        {
            getRequest();

            response();

            System.out.println("请求结束");
        }


        private void getRequest()
        {
            try
            {
                InputStream inputStream = socket.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);
                }

                System.out.println("获取header参数结束");

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }


        private void response()
        {
            try
            {
                OutputStream outputStream = socket.getOutputStream();

                PrintWriter writer = new PrintWriter(outputStream);

                writer.println("<h1>Hello World!</h1>");

                writer.flush();
                writer.close();
                outputStream.flush();
                outputStream.close();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
