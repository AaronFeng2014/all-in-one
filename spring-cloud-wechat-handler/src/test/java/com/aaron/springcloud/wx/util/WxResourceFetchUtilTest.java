package com.aaron.springcloud.wx.util;

import com.aaron.springcloud.wx.constants.MediaResourceTypeEnum;
import com.aaron.springcloud.wx.domain.MediaResourceRequest;
import com.aaron.springcloud.wx.domain.QrCode;
import com.aaron.springcloud.wx.menu.MenuButton;
import com.google.common.collect.ImmutableList;
import java.net.MalformedURLException;
import java.net.URL;
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
    public void uploadTemporaryMediaResource() throws MalformedURLException
    {
        String wxGroupUrl = "http://fdfs.test.ximalaya.com/group1/M00/4D/39/wKgDplvOxoWAL562AACUsZTVKXE621.jpg";
        MediaResourceRequest resource = new MediaResourceRequest(MediaResourceTypeEnum.IMAGE, new URL(wxGroupUrl), "");

        String accessToken = "15_OP0OHMtWoc7Tr8DGbeFxaFLtj_iCucRE_mvlwKlk2qfr2jQm4eZxiKGL1yEuLLUm31FNBSDa6HxBnG7oFlFNnIrsILL5fNvMVo5L8CjLlzc_Y9k4rdX6avf4SiwFr5_NKXlyyX3b7Pua3lBsLXUdAAABNK";

        WxResourceFetchUtil.uploadTemporaryMediaResource(resource, obj -> accessToken);
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
        String localToken = "";
        MenuButton menuButton = new MenuButton(localToken);

        //一级菜单
        MenuButton.ButtonBean buttonBean1 = new MenuButton.ButtonBean();
        buttonBean1.setType("view");
        buttonBean1.setName("我要开课");
        buttonBean1.setUrl("https://daka.ximalaya.com/admin/user/login");

        MenuButton.ButtonBean buttonBean2 = new MenuButton.ButtonBean();
        buttonBean2.setType("miniprogram");
        buttonBean2.setName("今日打卡");
        buttonBean2.setAppId("wx1a95b0a2ef72071f");
        buttonBean2.setPagePath("pages/todayTask/todayTask");
        buttonBean2.setUrl("pages/todayTask/todayTask");

        MenuButton.ButtonBean buttonBean3 = new MenuButton.ButtonBean();
        buttonBean3.setType("click");
        buttonBean3.setName("客服");
        buttonBean3.setKey("qrCode");

        ImmutableList<MenuButton.ButtonBean> firstMenu = ImmutableList.of(buttonBean2, buttonBean1, buttonBean3);

        menuButton.setButtons(firstMenu);

        System.out.println("菜单创建结果" + WxResourceFetchUtil.createMenu(menuButton));
    }
}