package com.aaron.enhance.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Aaron on 2016-02-13.
 */
public class SocketChannel
{

    private ServerSocket serverSocket;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private PrintWriter printWriter;


    @Test
    public void openServerSocket() throws IOException
    {
        try
        {
            serverSocket = new ServerSocket(9999);
            while (true)
            {
                socket = serverSocket.accept();
                SocketUtil.sendMessage(socket);
                //SocketUtil.receiveMessage(socket);
                socket.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        serverSocket.close();
    }
}
