package com.aaron.springcloud.gateway.ratelimit;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义的接口key解析，用于接口频率限制
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/31
 */
public class MyKeyResolver implements KeyResolver
{
    @Override
    public Mono<String> resolve(ServerWebExchange exchange)
    {
        /**
         * 1.基于ip限流：需要在header中增加远程访问者的ip
         * 2.基于用户限流：需要在header中添加用户信息
         * 3.基于接口限流等：需要在header中添加接口标记
         */
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getURI().getPath();
        return Mono.just(path);
    }
}