package com.aaron.wx.message.send;

import com.aaron.wx.message.CostumerMessage;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public final class MessageSend
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSend.class);

    private static RestTemplate restTemplate = new RestTemplate();


    private MessageSend()
    {

    }


    public static boolean sendCustomerMessage(CostumerMessage costumerMessage)
    {

        HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(costumerMessage));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(costumerMessage.getUrl(), requestEntity, String.class);

        String responseBody = responseEntity.getBody();

        LOGGER.info("微信服务器返回的状态值：{}", responseBody);

        return true;
    }


    public static boolean uploadMediaResource(CostumerMessage costumerMessage)
    {

        HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(costumerMessage));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(costumerMessage.getUrl(), requestEntity, String.class);

        String responseBody = responseEntity.getBody();

        LOGGER.info("微信服务器返回的状态值：{}", responseBody);

        return true;
    }
}
