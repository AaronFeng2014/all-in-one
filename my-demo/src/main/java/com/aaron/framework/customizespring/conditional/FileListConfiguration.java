package com.aaron.framework.customizespring.conditional;

import com.aaron.framework.customizespring.conditional.impl.LinuxLikeFileListServiceImpl;
import com.aaron.framework.customizespring.conditional.impl.WindowsFileListServiceImpl;
import com.aaron.framework.customizespring.conditional.matchcondition.LinuxLikeCondition;
import com.aaron.framework.customizespring.conditional.matchcondition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
@Configuration
public class FileListConfiguration
{
    /**
     * Conditional注解的使用
     * <p>
     * 注解里面的参数为 Class<? extends Condition> ，当Condition里面的match方法返回true时，执行被该注解标记的方法
     */
    @Bean
    @Conditional (WindowsCondition.class)
    public FileListService windows()
    {

        return new WindowsFileListServiceImpl();
    }


    @Bean
    @Conditional (LinuxLikeCondition.class)
    public FileListService linux()
    {

        return new LinuxLikeFileListServiceImpl();
    }
}
