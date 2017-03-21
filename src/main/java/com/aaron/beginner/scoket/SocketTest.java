package com.aaron.beginner.scoket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-26
 */
public class SocketTest
{
    public static void main(String[] args)
    {
        InputStream in = null;
        try
        {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();

            in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            Scanner scanner = new Scanner(in);
            PrintWriter printWriter = new PrintWriter(out);

            printWriter.println("HELLO, Enter 'BYE' to exit!");
            printWriter.flush();

            boolean done = false;

            while (!done && scanner.hasNext())
            {
                String line = scanner.nextLine();
                printWriter.println("response : " + line);
                printWriter.flush();

                if ("BYE".trim().equals(line))
                {
                    done = true;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
