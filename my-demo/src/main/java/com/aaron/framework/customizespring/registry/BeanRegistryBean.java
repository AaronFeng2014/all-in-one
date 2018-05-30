package com.aaron.framework.customizespring.registry;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * 该bean通过RegistryPostProcessor手动注册到spring context中
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/30
 */
@Setter
@Getter
public class BeanRegistryBean
{
    @Value ("${name}")
    private String name;

    private String school;


    public void init()
    {
        System.out.println("老牛逼了--------init-------");
    }
}
