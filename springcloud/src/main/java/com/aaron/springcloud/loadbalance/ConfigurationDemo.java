package com.aaron.springcloud.loadbalance;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-07-06
 */
@Configuration
public class ConfigurationDemo
{

    /**
     * 负载均衡配置
     *
     * @return RestTemplate
     */
    @LoadBalanced
    @Bean
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }


    /**
     * 多个RestTemplate时，使用@Primary注解标记
     *
     * @return RestTemplate
     */
    @Primary
    @Bean
    RestTemplate getBalance()
    {
        return new RestTemplate();
    }

}
