package com.aaron.framework.customizespring;

import com.aaron.framework.customizespring.context.MyApplicationContext;
import com.aaron.framework.customizespring.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

        StudentService bean = context.getBean(StudentService.class);
        bean.introduce("feng");

        Women women = context.getBean(Women.class);
        women.introduce();
    }
}
