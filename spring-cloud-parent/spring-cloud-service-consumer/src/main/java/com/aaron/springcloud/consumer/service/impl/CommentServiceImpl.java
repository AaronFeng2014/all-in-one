package com.aaron.springcloud.consumer.service.impl;

import com.aaron.springcloud.consumer.dao.CommentMapper;
import com.aaron.springcloud.consumer.service.CommentService;
import com.aaron.springcloud.entity.po.CommentPo;
import com.aaron.springcloud.entity.vo.CommentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<CommentPo> queryComment(Long phoneId)
    {
        return commentMapper.queryComment(phoneId);
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
