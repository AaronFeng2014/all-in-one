package com.aaron.springcloud.consumer.dao;

import com.aaron.springcloud.entity.po.PhoneInfo;
import com.aaron.springcloud.entity.po.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<PhoneInfo> queryPhoneInfo();


    /**
     * 根据手机id信息查询手机信息
     *
     * @param id Long：手机id信息
     * @return PhoneInfo：手机信息
     */
    PhoneInfo queryPhoneInfoById(@Param ("id") Long id);


    List<Picture> queryPhonePicture(@Param ("phoneIdList") List<Long> phoneIdList);

}
