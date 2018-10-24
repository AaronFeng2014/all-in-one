package com.aaron.springcloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring cloud gateway
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-05-25
 */
@SpringBootApplication
@EnableConfigurationProperties
@RestController
public class SpringCloudGatewayApplication
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudGatewayApplication.class);


    public static void main(String[] args)
    {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator rout(RouteLocatorBuilder builder)
    {

        return builder.routes().route(url -> {

            LOGGER.info("网关，url：{}", url.toString());

            /**
             * 下面配置的意思：
             * 把/consumer/**开始的请求都转发到7777端口下，即是ng中的反向代理
             */
            return url.path("/consumer/**").filters(header -> header.addResponseHeader("hello", "world")).uri("http://localhost:7777");

        }).build();
    }
}
