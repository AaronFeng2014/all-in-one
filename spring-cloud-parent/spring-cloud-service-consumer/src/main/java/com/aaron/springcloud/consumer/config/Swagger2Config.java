package com.aaron.springcloud.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger 2 API文档管理
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@Configuration
public class Swagger2Config
{
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(
                RequestHandlerSelectors.basePackage("com.aaron.springcloud")).paths(PathSelectors.any()).build();
    }


    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title("Web平台").description("手机展示网页Web平台").termsOfServiceUrl("http://localhost:7775").version("1.0")
                                   .build();
    }
}
