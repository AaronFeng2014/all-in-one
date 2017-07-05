package com.aaron.enhance.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.junit.Test;

/**
 * FileChannel:是一个连接到文件的通道，可以通过文件通道读写文件
 * FileChannel无法设置为非阻塞模式， 他总是运行在阻塞模式下
 */
public class FileChannelTest
{

    private final int BUFFER_SIZE_KB = 1024;
    private final int BUFFER_SIZE_MB = 1024 * 1024;
    private RandomAccessFile randomAccessFile;
    private final String path1 = "IntelliJ IDEA 15 Released 破解 注册码.txt";
    private FileChannel channel;
    private FileInputStream inputStream;


    @Test
    public void testReadFileFromFileChannel()
    {
        try
        {
            //打开FileChannel
            openFileChannel(path1);

            //设置缓存区的大小,单位为byte
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE_KB);

            //返回值为-1时，表示文件读取结束，其他值表示读取了多少字节
            int byteRead;
            long sum = 0L;
            while ((byteRead = channel.read(buffer)) != -1)
            {
                sum = sum + byteRead;
                System.out.println(sum + " current read " + byteRead);
                //切换读取和写入的状态
                buffer.flip();

            }

            char b = buffer.getChar(300);

            System.out.println("读取的内容是：" + b);

            //改方法获取当前位置，即当前读取文件到哪里了
            long position = channel.position();
            System.out.println("position = " + position);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    /**
     * 打开FileChannel
     * 无法直接打开FileChannel，必须通过InputStream，OutputStream或RandomAccessFile来获取FileChannel
     */
    public void openFileChannel(String filePath)
    {
        try
        {
            inputStream = new FileInputStream(new File(path1));
            randomAccessFile = new RandomAccessFile(filePath, "rw");
            //channel = randomAccessFile.getChannel();
            channel = inputStream.getChannel();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 通过FileChannel通道向文件写入数据
     */
    @Test
    public void testWriteDataByFileChannel() throws IOException
    {
        openFileChannel(path1);

        long size = channel.size();
        try
        {
            System.out.println("当前FileChannel关联的文件大小：" + channel.size() / 1024 + "KB");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE_KB);

        String testData = "This is a test for FileChannel, and this word write by file channel!";

        buffer.clear();
        //把数据放入buffer中
        buffer.put(testData.getBytes());

        buffer.flip();
        try
        {
            channel.position(channel.size());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        /**
         * FileChannel的write方法必须放在循环中使用
         *
         * 因为该方法不能保证每次写入多少数据，所以需要放入到循环中，
         * 直到buffer中的所有数据都被写入
         */
        while (buffer.hasRemaining())
        {
            try
            {
                //该方法不能保证数据被及时写入到磁盘，如果要及时写入需要force方法
                channel.write(buffer);
                //参数表示连源数据（文件权限等等）一并写入
                channel.force(true);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (size > 1024)
        {
            //截取文件，文件丢弃指定位置之后的所有内容
            channel.truncate(1024);
        }
    }


    @Test
    public void copyFileByChannel() throws IOException
    {
        String srcDirectory = "239561冯海鑫.xls";
        String url = URLDecoder.decode(this.getClass().getResource("/").getPath(), "utf-8");
        srcDirectory = url.substring(1) + srcDirectory;
        String destDirectory = url.substring(1) + "239561冯海鑫.xls_copy.xls";

        File destFile = new File(destDirectory);
        if (!destFile.exists())
        {
            if (destFile.createNewFile())
            {
                System.out.println("文件创建成功：" + destDirectory);
            }
        }

        RandomAccessFile in = new RandomAccessFile(srcDirectory, "rw");
        RandomAccessFile out = new RandomAccessFile(destDirectory, "rw");

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        ByteBuffer inBuffer = ByteBuffer.allocate(BUFFER_SIZE_MB);

        int read;
        while ((read = inChannel.read(inBuffer)) != -1)
        {
            System.out.println(read);

            //读写的状态切换的时候调用这句，这句话的作用主要是标记position = 0
            inBuffer.flip();
            outChannel.write(inBuffer);
            inBuffer.flip();
        }

        /*while (inBuffer.hasRemaining()) {
            outChannel.write(inBuffer);
        }*/
    }


    @Test
    public void testDirectory()
    {
        try
        {
            System.out.println(URLDecoder.decode(this.getClass().getClassLoader().getResource("dest.txt").getPath(), "utf-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}
