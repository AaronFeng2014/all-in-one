package com.aaron.springcloud.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-05-24
 */
@SpringBootApplication
@EnableEurekaClient
@Configuration
@PropertySource ({"classpath:jdbc.properties"})
@ComponentScan (basePackages = {"com.aaron.springcloud.consumer"})
@EnableFeignClients (basePackages = {"com.aaron"})
@MapperScan ("com.aaron.springcloud.consumer.dao")
public class PhoneShowAdminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PhoneShowAdminApplication.class, args);
    }
}
