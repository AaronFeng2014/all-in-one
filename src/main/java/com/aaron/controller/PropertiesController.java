package com.aaron.controller;

import com.aaron.util.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-16
 */

@Controller
@RequestMapping ("/property")
public class PropertiesController implements ApplicationContextAware
{

    private static final Log LOG = LogFactory.getLog(PropertiesController.class);

    private ApplicationContext context;


    @RequestMapping ("/{key}")
    @ResponseBody
    public String getProperty(@PathVariable String key)
    {

        try
        {

            LOG.warn(context.getBean("switchVariable"));
            LOG.warn(context.getBean("fileItemDemo"));

            return "{key:" + PropertyUtil.getProperty(key) + "}";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "{key:}";

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.context = applicationContext;
    }
}
