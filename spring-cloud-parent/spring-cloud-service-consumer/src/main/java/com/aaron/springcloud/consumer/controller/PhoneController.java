package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.consumer.service.PhoneService;
import com.aaron.springcloud.entity.po.PhoneInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基于Restful api设计
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@RestController
public class PhoneController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneController.class);

    /**
     * 分页大小
     */
    private static final int PAGE_SIZE = 50;

    @Autowired
    private PhoneService phoneService;


    @RequestMapping (value = "product", method = RequestMethod.GET)
    public List<PhoneInfo> queryPhoneInfoList(@RequestParam ("page") int page, @RequestParam ("pageSize") int pageSize)
    {

        List<PhoneInfo> phoneInfoList = phoneService.queryPhoneInfoList(page, pageSize);

        LOGGER.info("数据库查询手机信息：{}", phoneInfoList);
        return phoneInfoList;
    }


    @RequestMapping (value = "product/{phoneId}", method = RequestMethod.GET, produces = "application/json")
    public PhoneInfo queryPhoneInfo(@PathVariable ("phoneId") Long phoneId)
    {
        PhoneInfo phoneInfo = phoneService.queryPhoneInfoById(phoneId);
        LOGGER.info("数据库查询手机信息：{}", phoneInfo);
        return phoneInfo;
    }
}
