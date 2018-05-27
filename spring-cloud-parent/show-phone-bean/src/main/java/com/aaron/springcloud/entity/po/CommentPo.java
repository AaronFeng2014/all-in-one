package com.aaron.springcloud.entity.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 手机的评论信息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Setter
@Getter
@ToString
public class CommentPo
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
