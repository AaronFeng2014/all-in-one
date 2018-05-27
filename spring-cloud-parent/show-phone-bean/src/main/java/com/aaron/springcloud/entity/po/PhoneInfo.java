package com.aaron.springcloud.entity.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Setter
@Getter
@ToString
public class PhoneInfo
{

    private Long id;

    private String name;

    private String brand;

    private String description;

    /**
     * 手机的图片信息
     */
    private List<Picture> pictureList;

    /**
     * 手机的用户评论信息
     */
    private List<CommentPo> commentList;

    private Date createTime;
}
