package com.aaron.framework.customizespring;

import com.aaron.framework.customizespring.context.MyApplicationContext;
import com.aaron.framework.customizespring.registry.BeanRegistryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/14
 */
@Import (ImportBean.class)
@Configuration
public class CustmoizeTagSpringSample
{

    public static void main(String[] args)
    {
        ApplicationContext context = new MyApplicationContext("customize-spring.xml");

        System.out.println("bean ---->" + context.getBean(ImportBean.class));
        BeanRegistryBean bean = context.getBean(BeanRegistryBean.class);

        System.out.println(bean);
        System.out.println(context);
    }
}
