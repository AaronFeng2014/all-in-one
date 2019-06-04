package com.aaron.framework.customizespring.registry;

import com.aaron.framework.customizespring.importbean.aaron.component.Car;
import com.aaron.framework.customizespring.importbean.aaron.component.Desk;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
public class Sample
{
    public static void main(String[] args)
    {
        String packages = "com.aaron.framework.customizespring.registry";

        ApplicationContext context = new AnnotationConfigApplicationContext(packages);

        context.getBean(BeanRegistryBean.class);
        context.getBean(Car.class).run();
        context.getBean(Desk.class).run();
    }
}
