package com.aaron.springcloud.exception;

import org.springframework.http.HttpStatus;

/**
 * 资源异常， 未找到
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public class ResourceNotFoundException extends CustomizeException
{

    public ResourceNotFoundException(String message)
    {
        super(HttpStatus.NOT_FOUND, message);
    }

}
