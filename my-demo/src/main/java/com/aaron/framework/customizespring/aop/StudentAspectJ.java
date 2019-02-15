package com.aaron.framework.customizespring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-02-15
 */
@Component
@Aspect
public class StudentAspectJ
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAspectJ.class);


    @Pointcut ("execution(public * com.aaron.framework.customizespring.service.StudentService.*(String))")
    public void pointcut()
    {

    }


    @Around ("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        LOGGER.info("aop之前");
        return joinPoint.proceed();

    }
}
