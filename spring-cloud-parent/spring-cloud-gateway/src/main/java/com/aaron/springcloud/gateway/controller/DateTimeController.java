package com.aaron.springcloud.gateway.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/24
 */
@Component
public class DateTimeController
{
    public Mono<ServerResponse> dateHandler(ServerRequest request)
    {
        return ServerResponse.ok().body(Mono.just(request.headers().header("User-Agent").get(0)), String.class);
    }


    public Mono<ServerResponse> timeHandler(ServerRequest request)
    {
        return ServerResponse.ok().body(Mono.just(request.headers().header("Connection").get(0)), String.class);
    }
}
