package com.aaron.framework.mybatis.dao;

import com.aaron.framework.mybatis.entity.Config;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-23
 */
public class ConfigMapperTest
{
    private ApplicationContext context;


    @Before
    public void setUp() throws Exception
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    public void queryConfig() throws Exception
    {
        ConfigMapper mapper = (ConfigMapper)context.getBean("configMapper");
        PageHelper.startPage(3, 5);

        Page<Config> configs = mapper.queryConfig(new Config());

        System.out.println("=============================================" + configs.size());
        for (Config config : configs)
        {
            System.out.println(config);
        }
        System.out.println("=============================================");
        PageInfo<Config> pageInfo = new PageInfo<>(configs);

        System.out.println("totalPage = " + pageInfo.getPages());
        System.out.println("totalRecords = " + pageInfo.getTotal());
        System.out.println("pageSize = " + pageInfo.getPageSize());
        System.out.println("list = " + pageInfo.getList());
    }

}