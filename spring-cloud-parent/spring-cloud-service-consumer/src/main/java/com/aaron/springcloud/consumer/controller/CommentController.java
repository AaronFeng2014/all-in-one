package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.BaseController;
import com.aaron.springcloud.consumer.service.CommentService;
import com.aaron.springcloud.entity.po.CommentPo;
import com.aaron.springcloud.entity.vo.CommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@Api (description = "手机图片接口")
public class CommentController extends BaseController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;


    @ApiOperation (value = "手机评论查询", notes = "根据指定的手机id查询手机对应的评论")
    @ApiImplicitParams ({@ApiImplicitParam (name = "phoneId", value = "手机id", required = true, dataType = "long", paramType = "path"),
                                @ApiImplicitParam (name = "page",
                                                   value = "获取评论的页数",
                                                   required = true,
                                                   dataType = "int",
                                                   paramType = "path")})
    @RequestMapping (value = "comment/{phoneId}/{page}", method = RequestMethod.GET)
    public List<CommentPo> queryComment(@PathVariable ("phoneId") Long phoneId, @PathVariable ("page") Integer page)
    {
        LOGGER.info("查询评论的请求手机id：{}，当前查询第{}页", phoneId, page);

        List<CommentPo> commentPoList = commentService.queryComment(phoneId, page);

        LOGGER.info("查询手机评论信息：{}", commentPoList);

        return commentPoList;
    }


    @RequestMapping (value = "comment", method = RequestMethod.PUT)
    public ResponseEntity addComment(@RequestBody CommentVo commentVo, HttpServletRequest request)
    {

        String location = request.getProtocol() + request.getServerPort() + request.getContextPath();

        LOGGER.info("用户添加评论成功，新评论地址");
        commentService.addComment(commentVo);

        return ResponseEntity.ok(location);
    }
}
