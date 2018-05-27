package com.aaron.springcloud;

import com.aaron.springcloud.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public class BaseController
{

    @ExceptionHandler (Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e)
    {

        String message = e.getMessage();
        if (e instanceof CustomizeException)
        {
            HttpStatus status = ((CustomizeException)e).getStatus();

            return new ResponseEntity<>(message, status);
        }

        return new ResponseEntity<>("呃呃，服务器打盹了...", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
