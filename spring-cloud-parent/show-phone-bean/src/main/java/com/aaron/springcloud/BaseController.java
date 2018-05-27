package com.aaron.springcloud;

import com.aaron.springcloud.exception.CustomizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    @ExceptionHandler (Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e)
    {

        LOGGER.error("捕获到全局异常信息", e);
        String message = e.getMessage();
        if (e instanceof CustomizeException)
        {
            HttpStatus status = ((CustomizeException)e).getStatus();

            return new ResponseEntity<>(message, status);
        }

        return new ResponseEntity<>("呃呃，服务器打盹了...", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
