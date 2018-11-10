package com.aaron.springcloud.wx.util;

import com.aaron.springcloud.wx.domain.QrCode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/10
 */
public class WxResourceFetchUtilTest
{

    @Test
    public void getAccessToken()
    {
    }


    @Test
    public void getAccessTokenWithoutCache()
    {
    }


    @Test
    public void getAccessToken1()
    {
    }


    @Test
    public void sendCustomerMessage()
    {
    }


    @Test
    public void uploadTemporaryMediaResource()
    {
    }


    @Test
    public void uploadTemporaryMediaResource1()
    {
    }


    @Test
    public void createPermanentQrCodeWithOutCache()
    {
    }


    @Test
    public void createPermanentQrCode()
    {
    }


    @Test
    public void createPermanentQrCode1()
    {
    }


    @Test
    public void createTemporaryQrCodeWithOutCache()
    {
    }


    @Test
    public void createTemporaryQrCode() throws InterruptedException
    {
        QrCode qrCode = new QrCode("test-appId-unless", "test-scene");

        String localToken = "";
        Function<String, String> tokenFun = p -> localToken;

        for (int i = 0; i < 20; i++)
        {
            String url = WxResourceFetchUtil.createTemporaryQrCode(qrCode, tokenFun);

            System.out.println("获取到的二维码地址是：" + url);

            TimeUnit.SECONDS.sleep(5);
        }

    }


    @Test
    public void createTemporaryQrCode1()
    {
    }


    @Test
    public void createMiniProgramQrCode()
    {
    }


    @Test
    public void createMenu()
    {
    }
}