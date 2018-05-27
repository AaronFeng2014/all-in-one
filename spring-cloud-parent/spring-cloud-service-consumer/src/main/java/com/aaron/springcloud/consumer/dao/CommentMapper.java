package com.aaron.springcloud.consumer.dao;

import com.aaron.springcloud.entity.po.CommentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@Mapper
public interface CommentMapper
{
    /**
     * 查询一个产品的评论
     *
     * @param phoneId Long：手机id
     * @return
     */
    List<CommentPo> queryComment(@Param ("phoneId") Long phoneId);


    /**
     * 新增评论
     *
     * @param commentPo CommentPo：用户评论的内容
     */
    int addComment(CommentPo commentPo);
}
