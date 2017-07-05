package com.aaron.component.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfItem;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-15
 */
//该注解表示和配置中心关联的配置文件,像这种基于注解式的使用方式会自动刷新值，即当在配置中心修改了后，最新值会立马生效
@DisconfFile (filename = "status.properties")
@Component
@Setter
@ToString
public class SwitchVariable implements ApplicationContextAware
{

    public static ApplicationContext applicationContext;

    private String useCache;

    private String appName;

    private String address;


    @DisconfFileItem (name = "useCache", associateField = "useCache")
    public String getUseCache()
    {
        return useCache;
    }


    /**
     * 该注解表示和配置中心关联的文件中的key，以及与实体类中字段的映射关系
     * <p>
     * DisconfFileItem注解的name属性表示DisconfFile注解中指定的远程配置中心的配置文件coefficients.properties中的key
     * DisconfFileItem注解的associateField属性表示name属性指定的key和本地对象中的域的映射关系
     *
     * @return
     */
    @DisconfFileItem (name = "appName", associateField = "appName")
    public String getAppName()
    {
        return appName;
    }


    /**
     * DisconfItem注解是针对于配置中心的一个配置项而言
     *
     * @return
     */
    @DisconfItem (key = "address", associateField = "address")
    public String getAddress()
    {
        return address;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SwitchVariable.applicationContext = applicationContext;
    }


    public static void main(String[] args)
    {
        People people = new People();

        people.setName("q");
        people.setAge("12");

        change(people);

        System.out.println(people.getAge());


    }


    private static void change(People people)
    {
        People newPeople = people;

        newPeople.setAge("34");

        newPeople = new People();

        newPeople.setAge("2");
    }
}
