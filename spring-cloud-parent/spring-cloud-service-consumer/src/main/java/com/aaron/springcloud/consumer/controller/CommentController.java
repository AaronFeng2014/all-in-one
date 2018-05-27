package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.consumer.service.CommentService;
import com.aaron.springcloud.entity.po.CommentPo;
import com.aaron.springcloud.entity.vo.CommentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@RestController
public class CommentController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;


    @RequestMapping (value = "comment/{phoneId}", method = RequestMethod.GET)
    public List<CommentPo> queryComment(@PathVariable ("phoneId") Long phoneId)
    {

        List<CommentPo> commentPoList = commentService.queryComment(phoneId);

        LOGGER.info("查询手机评论信息：{}", commentPoList);
        return commentPoList;
    }


    @RequestMapping (value = "comment", method = RequestMethod.PUT)
    public void addComment(@RequestBody CommentVo commentVo)
    {

        commentService.addComment(commentVo);
    }


    @ExceptionHandler (value = Exception.class)
    public ResponseEntity exception()
    {

        ResponseEntity<String> entity = new ResponseEntity<>("请求失败", HttpStatus.BAD_GATEWAY);
        entity.getHeaders().add("href", "wwww.baidu.com");
        return entity;
    }
}
