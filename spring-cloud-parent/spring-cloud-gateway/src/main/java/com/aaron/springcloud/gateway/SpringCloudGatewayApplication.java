package com.aaron.springcloud.gateway;

import com.aaron.springcloud.gateway.ratelimit.MyKeyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
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
public class SpringCloudGatewayApplication
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudGatewayApplication.class);


    public static void main(String[] args)
    {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }


    @Autowired
    private RateLimiter rateLimiter;

    /**
     * 基于redis + Lua脚本实现的分布式接口访频率控制
     */
    private Consumer<RequestRateLimiterGatewayFilterFactory.Config> rateLimiterFilter = config -> {
        config.setRateLimiter(rateLimiter);

        config.setKeyResolver(new MyKeyResolver());
    };

    /**
     * GatewayFilterSpec.stripPrefix 可以移除网关地址中的部分前缀
     * <p>
     * 网关路由默认是和背后真实服务器地址一样
     * <p>
     * 这里使用了stripPrefix(1)，所以在  test/consumer/** 的地址路由的时候回移除 test
     */
    private Function<GatewayFilterSpec, UriSpec> consumerFilter = gatewayFilterSpec -> gatewayFilterSpec.stripPrefix(1)
                                                                                                        .addResponseHeader("hello", "world")
                                                                                                        .requestRateLimiter(
                                                                                                                rateLimiterFilter);

    /**
     * 下面配置的意思：
     * 把/consumer/**开始的请求都转发到7777端口下，即是ng中的反向代理
     */
    private Function<PredicateSpec, Route.AsyncBuilder> consumerRoute = predicateSpec -> predicateSpec.path("/test/consumer/**")
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


    @Bean
    public RedisRateLimiter redisRateLimiter()
    {
        /*
         * 第一个参数表示请求速率
         * 第二个参数表示总的允许请求的次数
         *
         * redis脚本中解析这两个参数：
         * 总次数 / 速率 * 2 在这个时间窗口内，还没有达到总的请求量的话，就清空之前的统计信息，重新计算
         * 但是如果在这个时间窗口内某个秒达到了最大请求次数的话，在这秒内再次请求会拒绝请求，间隔几面后，会增加剩余可请求次数
         *
         * 增加的次数=间隔时间秒数 * 请求速率
         */
        return new RedisRateLimiter(5, 25);
    }
}
