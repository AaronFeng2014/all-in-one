package com.aaron.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-05-24
 */
@SpringBootApplication
@EnableEurekaClient
@Configuration
@ComponentScan
@EnableFeignClients (basePackages = {"com.aaron.springcloud"})
public class SpringCloudServiceConsumerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringCloudServiceConsumerApplication.class, args);
    }
}
