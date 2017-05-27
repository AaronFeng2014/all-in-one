package com.aaron.framework.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-23
 */

@Setter
@Getter
@ToString
public class Config
{

    private Long configId;

    private Integer type;

    private String name;

    private String value;

    private Integer appId;

    private Integer version;

    private Integer envId;

    private String createTime;

    private String updateTime;

    private Integer status;
}
