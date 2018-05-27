package com.aaron.springcloud.consumer.service.impl;

import com.aaron.springcloud.consumer.dao.ProductMapper;
import com.aaron.springcloud.consumer.service.PhoneService;
import com.aaron.springcloud.entity.po.PhoneInfo;
import com.aaron.springcloud.entity.vo.PhoneInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int addPhoneInfo(PhoneInfoVo phoneInfoVo)
    {
        PhoneInfo phoneInfo = new PhoneInfo();
        phoneInfo.setBrand(phoneInfoVo.getBrand());
        phoneInfo.setName(phoneInfoVo.getName());
        phoneInfo.setDescription(phoneInfoVo.getDescription());
        return productDao.addPhoneInfo(phoneInfo);
    }
}
