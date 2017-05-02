package com.aaron.enhance.nio;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Aaron on 2016-02-13.
 */
public class Client
{

    private static Socket socket;


    public static void main(String[] args)
    {

        try
        {
            while (true)
            {
                socket = new Socket("localhost", 9999);

                //SocketUtil.sendMessage(socket);

                SocketUtil.receiveMessage(socket);

                Thread.sleep(100);
                socket.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
