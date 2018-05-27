package com.aaron.springcloud.consumer.service;

import com.aaron.springcloud.entity.po.CommentPo;
import com.aaron.springcloud.entity.vo.CommentVo;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public interface CommentService
{
    List<CommentPo> queryComment(Long phoneId, int page);


    void addComment(CommentVo commentVo);
}
