package com.aaron.springcloud.consumer.aspect;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户权限校验切面
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/28
 */
@Component
@Aspect
@Order (1)
public class AuthorizeAspect
{

    /**
     * 基于注解标记的切面表达式，用于标记需要鉴权的接口
     */
    @Pointcut ("@annotation(com.aaron.springcloud.consumer.NeedAuth)")
    public void expression()
    {

    }


    @Around (value = "expression()")
    public ResponseEntity around(ProceedingJoinPoint joinPoint) throws Throwable
    {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token))
        {
            return new ResponseEntity<>("权限认证失败", HttpStatus.UNAUTHORIZED);

        }

        return (ResponseEntity)joinPoint.proceed();
    }

}

