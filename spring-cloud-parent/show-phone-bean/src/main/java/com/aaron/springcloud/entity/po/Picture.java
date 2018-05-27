package com.aaron.springcloud.entity.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 和手机关联的图片信息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Setter
@Getter
@ToString
public class Picture
{
    private Long id;

    private Long phoneId;

    private String name;

    private String url;
}
