package com.aaron.framework.customizespring.conditional.impl;

import com.aaron.framework.customizespring.conditional.FileListService;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
public class LinuxLikeFileListServiceImpl implements FileListService
{
    @Override
    public String getCommand()
    {
        return "ls /";
    }
}
