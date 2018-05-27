package com.aaron.springcloud.exception;

import org.springframework.http.HttpStatus;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public class ResourceNotFoundException extends CustomizeException
{
    private String message;

    private int code = HttpStatus.NOT_FOUND.value();


    public ResourceNotFoundException(String message)
    {

        this.message = message;
    }

}
