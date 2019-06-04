package com.aaron.framework.customizespring.importbean.aaron.component;

import com.aaron.framework.customizespring.importbean.aaron.Feng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
@Feng
public class Car
{

    private static final Logger LOGGER = LoggerFactory.getLogger(Car.class);


    public void run()
    {
        LOGGER.info("car run with oil");
    }
}
