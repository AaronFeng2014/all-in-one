package com.aaron.springcloud.consumer.service.impl;

import com.aaron.springcloud.consumer.dao.ProductMapper;
import com.aaron.springcloud.consumer.service.PhoneService;
import com.aaron.springcloud.entity.po.PhoneInfo;
import com.aaron.springcloud.entity.po.Picture;
import com.aaron.springcloud.exception.ResourceNotFoundException;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Service
public class PhoneServiceImpl implements PhoneService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneServiceImpl.class);

    @Autowired
    private ProductMapper productDao;


    @Override
    public List<PhoneInfo> queryPhoneInfoList(int page, int pageSize)
    {

        LOGGER.info("page:{}, pageSize:{}", page, pageSize);
        PageHelper.startPage(page, pageSize);

        List<PhoneInfo> phoneInfoList = productDao.queryPhoneInfo();

        if (!CollectionUtils.isEmpty(phoneInfoList))
        {
            List<Long> idList = phoneInfoList.stream().map(PhoneInfo::getId).collect(Collectors.toList());

            List<Picture> pictures = productDao.queryPhonePicture(idList);

            if (!CollectionUtils.isEmpty(pictures))
            {
                //转成map集合
                Map<Long, List<Picture>> pictureMap = new HashMap<>();
                pictures.forEach(picture -> {

                    pictureMap.computeIfAbsent(picture.getPhoneId(), k -> new ArrayList<>());

                    pictureMap.get(picture.getPhoneId()).add(picture);
                });

                phoneInfoList.forEach(phoneInfo -> phoneInfo.setPictureList(pictureMap.getOrDefault(phoneInfo.getId(), new ArrayList<>())));
            }

        }
        return phoneInfoList;
    }


    @Override
    public PhoneInfo queryPhoneInfoById(Long phoneId)
    {
        PhoneInfo phoneInfo = productDao.queryPhoneInfoById(phoneId);

        if (phoneInfo == null)
        {
            throw new ResourceNotFoundException("未查询到手机信息");
        }

        List<Picture> pictures = productDao.queryPhonePicture(ImmutableList.of(phoneId));

        phoneInfo.setPictureList(pictures);
        return phoneInfo;
    }
}
