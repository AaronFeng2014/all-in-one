package com.aaron.springcloud.wx.util;

import com.aaron.springcloud.wx.constants.MediaResourceTypeEnum;
import com.aaron.springcloud.wx.domain.MediaResourceRequest;
import com.aaron.springcloud.wx.domain.QrCode;
import com.aaron.springcloud.wx.menu.MenuButton;
import com.aaron.springcloud.wx.message.TemplateMessage;
import com.aaron.springcloud.wx.message.TextMessage;
import com.aaron.springcloud.wx.message.msgbody.Text;
import com.google.common.collect.ImmutableList;
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

        String localToken = "15_j8menNhcLyQimo9rYWPQgfUvK0ypHqtfvZbNef48YoLNH5CACJmC5GmoWHRp_DsOJIzIAib9bTJ9DUyqx5Kxww5eZN_h0vQJkOCvTpkN7e8tIp9dYYBviHGk21g0fMkSKSMH5pjVDIJD2nWBVNMiAAAGYX";

        TextMessage message = new TextMessage(new Text("test"), localToken);
        message.setTouser("oQJo34yjSF3vL-zrbNPbFUlPJzSw");
        WxResourceUtil.sendCustomerMessage(message);
    }


    @Test
    public void uploadTemporaryMediaResource() throws Exception
    {
        String wxGroupUrl = "http://fdfs.test.ximalaya.com/group1/M00/4D/39/wKgDplvOxoWAL562AACUsZTVKXE621.jpg";
        MediaResourceRequest resource = new MediaResourceRequest(MediaResourceTypeEnum.IMAGE, wxGroupUrl, "");

        String localToken = "15_j8menNhcLyQimo9rYWPQgfUvK0ypHqtfvZbNef48YoLNH5CACJmC5GmoWHRp_DsOJIzIAib9bTJ9DUyqx5Kxww5eZN_h0vQJkOCvTpkN7e8tIp9dYYBviHGk21g0fMkSKSMH5pjVDIJD2nWBVNMiAAAGYX";
        Function<String, String> tokenFun = p -> localToken;

        for (int i = 0; i < 20; i++)
        {

            new Thread(() -> {

                WxResourceUtil.uploadTemporaryMediaResource(resource, tokenFun);

            }).start();

        }
        TimeUnit.MINUTES.sleep(5);
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

        String localToken = "15_j8menNhcLyQimo9rYWPQgfUvK0ypHqtfvZbNef48YoLNH5CACJmC5GmoWHRp_DsOJIzIAib9bTJ9DUyqx5Kxww5eZN_h0vQJkOCvTpkN7e8tIp9dYYBviHGk21g0fMkSKSMH5pjVDIJD2nWBVNMiAAAGYX";
        Function<String, String> tokenFun = p -> localToken;

        for (int i = 0; i < 20; i++)
        {

            new Thread(() -> {

                String url = WxResourceUtil.createTemporaryQrCode(qrCode, tokenFun);

                System.out.println("获取到的二维码地址是：" + url);

            }).start();

        }
        TimeUnit.MINUTES.sleep(5);

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

        System.out.println("菜单创建结果" + WxResourceUtil.createMenu(menuButton));
    }


    @Test
    public void sendTemplateMessage()
    {
        String localToken = "15_j8menNhcLyQimo9rYWPQgfUvK0ypHqtfvZbNef48YoLNH5CACJmC5GmoWHRp_DsOJIzIAib9bTJ9DUyqx5Kxww5eZN_h0vQJkOCvTpkN7e8tIp9dYYBviHGk21g0fMkSKSMH5pjVDIJD2nWBVNMiAAAGYX";

        String openId = "oQJo34yjSF3vL-zrbNPbFUlPJzSw";
        TemplateMessage message = new TemplateMessage(openId, "", null);
        WxResourceUtil.sendTemplateMessage(message, localToken);
    }

}