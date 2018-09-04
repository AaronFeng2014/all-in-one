package com.aaron.netty.ftp.server.service.impl;

import com.aaron.netty.ftp.server.service.FileService;
import java.io.File;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件服务类
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
@Service
public class FileServiceImpl implements FileService
{
    @Override
    public List<String> getAllFileList(File file)
    {
        if (!file.isDirectory())
        {
            throw new RuntimeException("目标地址是一个文件，请输入一个文件夹地址");
        }

        File[] files = file.listFiles();

        if (ArrayUtils.isEmpty(files))
        {
            return Collections.emptyList();
        }

        //按文件和文件夹分类一波
        Map<Boolean, List<File>> collect = Stream.of(files).collect(Collectors.groupingBy(File::isDirectory));

        Function<File, String> function = File::getPath;
        //文件夹
        List<String> directoryList = collect.getOrDefault(true, Collections.emptyList())
                                            .stream()
                                            .map(function)
                                            .sorted()
                                            .collect(Collectors.toList());

        //文件
        List<String> fileList = collect.getOrDefault(false, Collections.emptyList())
                                       .stream()
                                       .map(function)
                                       .sorted()
                                       .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        result.addAll(directoryList);
        result.addAll(fileList);

        return result;

    }
}
