package com.aaron.framework.mybatis.dao;

import com.aaron.framework.mybatis.entity.Config;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-23
 */
@Repository
public interface ConfigMapper
{
    Page<Config> queryConfig(Config config);
}
