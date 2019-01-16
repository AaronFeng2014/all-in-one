package com.aaron.component.netty.ftp.server.service;

import java.io.File;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
public interface FileService
{

    List<String> getAllFileList(File file);
}
