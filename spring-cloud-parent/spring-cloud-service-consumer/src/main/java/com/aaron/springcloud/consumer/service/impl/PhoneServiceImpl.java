package com.aaron.springcloud.consumer.service.impl;

import com.aaron.springcloud.consumer.dao.ProductMapper;
import com.aaron.springcloud.consumer.service.PhoneService;
import com.aaron.springcloud.entity.po.PhoneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Service
public class PhoneServiceImpl implements PhoneService
{

    @Autowired
    private ProductMapper productDao;


    @Override
    public List<PhoneInfo> queryPhoneInfoList()
    {
        return productDao.queryPhoneInfo();
    }


    @Override
    public PhoneInfo queryPhoneInfoById(Long phoneId)
    {
        return productDao.queryPhoneInfoById(phoneId);
    }
}
