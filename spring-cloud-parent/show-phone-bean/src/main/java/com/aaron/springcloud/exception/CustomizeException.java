package com.aaron.springcloud.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@Setter
@Getter
@ToString
public class CustomizeException extends RuntimeException
{
    private HttpStatus status;


    CustomizeException(HttpStatus status, String message)
    {
        super(message);
        this.status = status;
    }
}
