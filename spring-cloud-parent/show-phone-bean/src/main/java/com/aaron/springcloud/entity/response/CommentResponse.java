package com.aaron.springcloud.entity.response;

import com.aaron.springcloud.entity.vo.CommentVo;
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
public class CommentResponse
{
    private String location;

    private CommentVo commentVo;
}
