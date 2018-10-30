package com.aaron.springcloud.gateway.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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


    public Mono<ServerResponse> currentTimeHandler(ServerRequest request)
    {

        return ServerResponse.ok()
                             .contentType(MediaType.TEXT_EVENT_STREAM)
                             .body(Flux.interval(Duration.ofSeconds(1L)).map(r -> LocalDateTime.now().toString()), String.class);
    }
}
