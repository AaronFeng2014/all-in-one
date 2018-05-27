package com.aaron.springcloud.consumer.service;

import com.aaron.springcloud.entity.po.PhoneInfo;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
public interface PhoneService
{
    List<PhoneInfo> queryPhoneInfoList();


    PhoneInfo queryPhoneInfoById(Long phoneId);
}
