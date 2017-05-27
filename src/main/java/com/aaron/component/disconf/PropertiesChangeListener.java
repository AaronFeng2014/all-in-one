package com.aaron.component.disconf;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-16
 */
@Component
@DisconfUpdateService (classes = {SwitchVariable.class})
//@DisconfUpdateService (confFileKeys = {"coe.baiFaCoe"})
public class PropertiesChangeListener implements IDisconfUpdate
{
    @Override
    public void reload() throws Exception
    {
        System.out.println("远程配置中心配置已更改");
    }
}
