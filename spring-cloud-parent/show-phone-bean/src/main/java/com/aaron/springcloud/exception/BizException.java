package com.aaron.springcloud.exception;

import org.springframework.http.HttpStatus;

/**
 * 业务异常
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public class BizException extends CustomizeException
{
    public BizException(String message)
    {
        super(HttpStatus.REQUEST_TIMEOUT, message);
    }
}
