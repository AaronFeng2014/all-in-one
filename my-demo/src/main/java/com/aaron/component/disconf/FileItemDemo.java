package com.aaron.component.disconf;

import com.baidu.disconf.client.common.annotations.DisconfItem;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-18
 */
@Component
@ToString
@Setter
public class FileItemDemo
{

    private String rate;


    @DisconfItem (key = "rate", associateField = "rate")
    public String getRate()
    {
        return rate;
    }
}
