package com.aaron.springcloud.loadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-07-06
 */
public class LoadBalanceDemo
{
    @Autowired
    private RestTemplate restTemplate;


    public String doAction()
    {
        String result = restTemplate.getForObject("http://aaron.test.io", String.class);

        return result;
    }
}
