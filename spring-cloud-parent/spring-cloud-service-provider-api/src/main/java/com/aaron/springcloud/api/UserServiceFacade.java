package com.aaron.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基于Feign的http服务申明
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/24
 */
@FeignClient (name = "spring-cloud-service-provider")
public interface UserServiceFacade
{
    @RequestMapping (value = "/user/{userId}", method = RequestMethod.POST)
    @ResponseBody
    Student queryUserInfo(@PathVariable ("userId") Long userId);
}
