package com.aaron.springcloud.consumer.dao;

import com.aaron.springcloud.entity.po.PhoneInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 手机信息查询dao
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/26
 */
@Mapper
public interface ProductMapper
{
    /**
     * 这里分页查询吧
     *
     * @return List<PhoneInfo>：手机信息
     */
    int addPhoneInfo(PhoneInfo phoneInfo);

}
