package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.BaseController;
import com.aaron.springcloud.consumer.NeedAuth;
import com.aaron.springcloud.consumer.service.PhoneService;
import com.aaron.springcloud.entity.vo.PhoneInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于Restful api设计
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@RestController
public class PhoneController extends BaseController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneService phoneService;


    @NeedAuth
    @RequestMapping (value = "product", method = RequestMethod.PUT)
    public ResponseEntity<String> addPhoneInfo(@RequestBody PhoneInfoVo phoneInfoVo)
    {

        int total = phoneService.addPhoneInfo(phoneInfoVo);

        LOGGER.info("数据库插入手机信息：{}条", total);

        return ResponseEntity.ok("新增手机信息成功");
    }

}
