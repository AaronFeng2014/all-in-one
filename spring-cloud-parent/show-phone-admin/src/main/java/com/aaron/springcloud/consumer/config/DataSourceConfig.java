package com.aaron.springcloud.consumer.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Configuration
//@PropertySource ("classpath:jdbc.properties")
//@ConfigurationProperties
@EnableAsync
public class DataSourceConfig
{

    @Value ("${jdbc.phone.username}")
    private String userName;

    @Value ("${jdbc.phone.url}")
    private String url;

    @Value ("${jdbc.phone.password}")
    private String password;

    @Value ("${driverClassName}")
    private String driverClassName;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean ("dataSource")
    public DataSource getDataSource()
    {

        DruidDataSource source = new DruidDataSource();

        source.setUsername("phone");
        source.setPassword("phone");
        source.setUrl("jdbc:mysql://192.168.1.159:3306/phone?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL");
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return source;

    }


    @Bean ("SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Autowired DataSource dataSource) throws Exception
    {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();

        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("autoRuntimeDialect", "truel");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        factoryBean.setPlugins(new Interceptor[] {pageHelper});

        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

        return factoryBean.getObject();
    }


    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(SqlSessionFactory sqlSessionFactory)
    {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();

        configurer.setSqlSessionFactory(sqlSessionFactory);
        configurer.setBasePackage("con.aaron.springcloud.mapper");

        return configurer;

    }
}
