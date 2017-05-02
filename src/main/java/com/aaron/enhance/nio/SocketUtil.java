package com.aaron.enhance.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Aaron on 2016-02-14.
 */
public final class SocketUtil
{

    public static void sendMessage(Socket socket)
    {
        try
        {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print("computer:shutdown -a");
            printWriter.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void receiveMessage(Socket socket)
    {
        try
        {
            //            InputStream inputStream = socket.getInputStream();
            //            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //            String line = reader.readLine();
            //            String command = line.substring(line.lastIndexOf(":") + 1);

            SocketChannel channel = socket.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(96);
            int read;
            while ((read = channel.read(byteBuffer)) != -1)
            {
                System.out.println(read);
            }

            //            executeCommand(command);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    private static void executeCommand(String command)
    {
        Runtime runtime = Runtime.getRuntime();
        try
        {
            runtime.exec(command);
            long maxMemory = runtime.maxMemory();
            System.out.println("Max Memory : " + maxMemory);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
