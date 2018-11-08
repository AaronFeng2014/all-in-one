package com.aaron.springcloud.wx.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 创建菜单内容
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Setter
@Getter
public class MenuButton
{

    @JSONField (name = "button")
    private List<ButtonBean> buttons;


    @Setter
    @Getter
    public static class ButtonBean
    {
        /**
         * type : click
         * name : 今日歌曲
         * key : V1001_TODAY_MUSIC
         * sub_button : [{"type":"view","name":"搜索","url":"http://www.soso.com/"},{"type":"miniprogram","name":"wxa","url":"http://mp.weixin.qq.com","appid":"wx286b93c14bbf93aa","pagepath":"pages/lunar/index"},{"type":"click","name":"赞一下我们","key":"V1001_GOOD"}]
         */

        private String type;

        private String name;

        private String key;

        @JSONField (name = "sub_button")
        private List<SubButtonBean> subButton;


        @Setter
        @Getter
        public static class SubButtonBean
        {
            /**
             * type : view
             * name : 搜索
             * url : http://www.soso.com/
             * appid : wx286b93c14bbf93aa
             * pagepath : pages/lunar/index
             * key : V1001_GOOD
             */

            private String type;

            private String name;

            private String url;

            @JSONField (name = "appid")
            private String appId;

            @JSONField (name = "pagepath")
            private String pagePath;

            private String key;
        }
    }


    public static void main(String[] args)
    {
        MenuButton menuButton = new MenuButton();

        ButtonBean buttonBean = new ButtonBean();
        buttonBean.setKey("key");
        buttonBean.setName("name");
        buttonBean.setType("click");

        menuButton.setButtons(ImmutableList.of(buttonBean, buttonBean));

        ButtonBean.SubButtonBean subButtonBean = new ButtonBean.SubButtonBean();
        subButtonBean.setAppId("appId");
        subButtonBean.setKey("key");
        subButtonBean.setName("subname");
        subButtonBean.setPagePath("www.baidu.com");
        subButtonBean.setUrl("www");
        subButtonBean.setType("VIEW");

        buttonBean.setSubButton(ImmutableList.of(subButtonBean, subButtonBean, subButtonBean));

        System.out.println(JSON.toJSONString(menuButton, SerializerFeature.PrettyFormat));
    }
}
