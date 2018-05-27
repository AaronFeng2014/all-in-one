package com.aaron.springcloud.consumer.service.impl;

import com.aaron.springcloud.consumer.dao.CommentMapper;
import com.aaron.springcloud.consumer.service.CommentService;
import com.aaron.springcloud.entity.po.CommentPo;
import com.aaron.springcloud.entity.vo.CommentVo;
import com.aaron.springcloud.exception.ResourceNotFoundException;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@Service
public class CommentServiceImpl implements CommentService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    /**
     * 查询评论的分页大小
     */
    private static final int COMMENT_PAGE_SIZE = 2;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<CommentPo> queryComment(Long phoneId, int page)
    {
        PageHelper.startPage(page, COMMENT_PAGE_SIZE);

        List<CommentPo> commentPoList = commentMapper.queryComment(phoneId);

        if (CollectionUtils.isEmpty(commentPoList))
        {
            throw new ResourceNotFoundException("无评论信息");
        }
        return commentPoList;
    }


    @Override
    public void addComment(CommentVo commentVo)
    {

        CommentPo commentPo = new CommentPo();
        commentPo.setPhoneId(commentVo.getPhoneId());
        commentPo.setAuthor(commentVo.getAuthor());
        commentPo.setContent(commentVo.getContent());

        int total = commentMapper.addComment(commentPo);

        LOGGER.info("插入记录数：{}条", total);

    }
}
