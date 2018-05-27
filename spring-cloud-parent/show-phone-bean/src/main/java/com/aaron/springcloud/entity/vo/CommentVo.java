package com.aaron.springcloud.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@Setter
@Getter
@ToString
public class CommentVo
{
    private Long phoneId;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 提交评论的作者
     */
    private String author;
}
