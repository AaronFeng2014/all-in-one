package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.consumer.utils.TokenUtil;
import com.aaron.springcloud.entity.vo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登陆
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/28
 */
@RestController
public class UserController
{
    public ResponseEntity login(User user)
    {

        String token = TokenUtil.generateToken(user);

        return ResponseEntity.ok("token");
    }
}
