package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.exception.BizException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public class BaseController
{

    public ResponseEntity<String> exceptionHandler(Exception e)
    {

        if (e instanceof BizException)
        {

        }

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);

    }
}
