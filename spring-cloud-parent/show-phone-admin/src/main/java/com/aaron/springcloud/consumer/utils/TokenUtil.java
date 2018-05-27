package com.aaron.springcloud.consumer.utils;

import com.aaron.springcloud.entity.vo.User;

/**
 * 用户token获取工具类
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/28
 */
public final class TokenUtil
{

    /**
     * des算法密钥
     */
    private static final String PRIVATE_KEY = "aibuaiwo";


    private TokenUtil()
    {

    }


    /**
     * 使用des加密算法
     * 根据登陆用户信息获取token，用于在其他接口中直接传递token，而不是传递用户名和密码，增加接口的安全性能
     *
     * @param user User：登陆的用户信息
     * @return String：根据登陆用户算出来的token信息
     */
    public static String generateToken(User user)
    {

        return "";
    }

}
