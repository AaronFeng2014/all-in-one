package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.consumer.utils.UUIDUtil;
import com.aaron.springcloud.entity.vo.PictureVo;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
@RestController
public class PictureController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    private static final String FILE_DIRECTORY = "/Users/fenghaixin/Desktop/";


    @RequestMapping (value = "picture/{phoneId}", method = RequestMethod.POST)
    public ResponseEntity uploadPicture(@RequestParam ("file") MultipartFile file, @PathVariable ("phoneId") Long phoneId)
    {

        String originalFilename = file.getOriginalFilename();

        String newName = UUIDUtil.getUuid() + "." + FilenameUtils.getExtension(originalFilename);
        LOGGER.info("上传的文件名：{}", originalFilename);

        PictureVo pictureVo = new PictureVo();
        pictureVo.setPhoneId(phoneId);
        pictureVo.setName("");
        pictureVo.setUrl(newName);

        try
        {
            //写到本地，这里也可以使用fdfs来处理图片信息
            FileUtils.writeByteArrayToFile(new File(FILE_DIRECTORY + newName), file.getBytes());
        }
        catch (IOException e)
        {
            LOGGER.error("文件写入本地磁盘错误：", e);
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        //给新加的资源返回一个地址信息，保存在header中
        headers.add("href", "http://www.baidu.com");

        return new ResponseEntity<>("图片上传成功", headers, HttpStatus.OK);
    }
}
