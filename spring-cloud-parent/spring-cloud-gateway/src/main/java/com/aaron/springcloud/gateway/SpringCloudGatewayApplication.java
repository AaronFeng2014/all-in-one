package com.aaron.springcloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

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


    private Function<GatewayFilterSpec, UriSpec> consumerFilter = gatewayFilterSpec -> gatewayFilterSpec.addResponseHeader("hello",
                                                                                                                           "world");

    /**
     * 下面配置的意思：
     * 把/consumer/**开始的请求都转发到7777端口下，即是ng中的反向代理
     */
    private Function<PredicateSpec, Route.AsyncBuilder> consumerRoute = predicateSpec -> predicateSpec.path("/consumer/**")
                                                                                                      .filters(consumerFilter)
                                                                                                      .uri("http://7777.local.com:7777");

    private Function<PredicateSpec, Route.AsyncBuilder> providerRoute = predicateSpec -> predicateSpec.path("/provider/**")
                                                                                                      .filters(consumerFilter)
                                                                                                      .uri("http://7776.local.com:7776");


    @Bean
    public RouteLocator rout(RouteLocatorBuilder builder)
    {

        return builder.routes().route(consumerRoute).route(providerRoute).build();
    }
}
