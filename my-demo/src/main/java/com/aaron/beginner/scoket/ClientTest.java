package com.aaron.beginner.scoket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-26
 */
public class ClientTest
{

    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("localhost",9999);
            OutputStream outputStream = socket.getOutputStream();

            InputStream inputStream = socket.getInputStream();

            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("我是客户端，请求连接");
            printWriter.flush();
            Scanner scanner = new Scanner(inputStream);
            while (true)
            {
                if (scanner.hasNext())
                {
                    printWriter.println(scanner.nextLine());
                    printWriter.flush();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
