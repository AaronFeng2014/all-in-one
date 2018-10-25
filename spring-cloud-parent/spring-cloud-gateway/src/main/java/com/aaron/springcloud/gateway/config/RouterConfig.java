package com.aaron.springcloud.gateway.config;

import com.aaron.springcloud.gateway.controller.DateTimeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/24
 */
@Configuration
public class RouterConfig
{
    @Autowired
    private DateTimeController dateTimeController;


    @Bean
    public RouterFunction<ServerResponse> getDate()
    {
        return RouterFunctions.route(RequestPredicates.GET("/date"), request -> dateTimeController.dateHandler(request));
    }


    @Bean
    public RouterFunction<ServerResponse> getTime()
    {
        return RouterFunctions.route(RequestPredicates.GET("/time"), request -> dateTimeController.timeHandler(request));
    }


    @Bean
    public RouterFunction<ServerResponse> now()
    {
        return RouterFunctions.route(RequestPredicates.GET("/now"), request -> dateTimeController.currentTimeHandler(request));
    }
}
