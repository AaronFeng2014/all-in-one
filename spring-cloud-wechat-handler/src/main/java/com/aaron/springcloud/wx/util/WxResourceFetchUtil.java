package com.aaron.springcloud.wx.util;

import com.aaron.springcloud.wx.cache.MediaCache;
import com.aaron.springcloud.wx.cache.QrCodeCache;
import com.aaron.springcloud.wx.cache.impl.memory.MediaMemoryCacheRepository;
import com.aaron.springcloud.wx.cache.impl.memory.QrCodeMemoryCacheRepository;
import com.aaron.springcloud.wx.constants.MediaResourceTypeEnum;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.domain.MediaResourceRequest;
import com.aaron.springcloud.wx.domain.MiniProgramQrCodeParam;
import com.aaron.springcloud.wx.domain.QrCode;
import com.aaron.springcloud.wx.domain.QrCodeCacheItem;
import com.aaron.springcloud.wx.domain.QrCodeRequest;
import com.aaron.springcloud.wx.domain.TemporaryQrCodeRequest;
import com.aaron.springcloud.wx.exception.WxException;
import com.aaron.springcloud.wx.menu.MenuButton;
import com.aaron.springcloud.wx.message.CostumerMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import java.util.function.Function;

/**
 * 获取微信端资源工具类
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-07
 */
public final class WxResourceFetchUtil extends BaseUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WxResourceFetchUtil.class);

    /**
     * 默认的媒体资源内存缓存实现
     */
    private static final MediaCache DEFAULT_MEDIA_CACHE = new MediaMemoryCacheRepository();

    /**
     * 默认的二维码内存缓存实现
     */
    private static final QrCodeCache DEFAULT_QRCODE_CACHE = new QrCodeMemoryCacheRepository();


    private WxResourceFetchUtil()
    {

    }


    /**
     * 微信小程序和服务号客服消息发送
     *
     * @param costumerMessage CostumerMessage：待发送的客服消息
     *
     * @return 发送成功时返回true，否则返回false
     */
    public static boolean sendCustomerMessage(CostumerMessage costumerMessage)
    {
        try
        {

            HttpEntity<CostumerMessage> requestEntity = new HttpEntity<>(costumerMessage);
            JSONObject jsonObject = extractResponse(REST_TEMPLATE.postForEntity(costumerMessage.getUrl(), requestEntity, String.class));

            return isSuccess(jsonObject);
        }
        catch (RestClientException e)
        {
            LOGGER.error("发送客服消息异常", e);
            return false;
        }
    }


    /**
     * 微信媒体资源上传，使用默认的内存缓存
     *
     * @param mediaResource MediaResource：素材资源信息
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     *
     * @return
     */
    public static String uploadTemporaryMediaResource(MediaResourceRequest mediaResource, Function<String, String> accessTokenFun)
    {
        return uploadTemporaryMediaResource(mediaResource, accessTokenFun, DEFAULT_MEDIA_CACHE);
    }


    /**
     * 微信媒体资源上传，该方法可以自定义缓存实现
     *
     * @param mediaResource MediaResource：素材资源信息
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     *
     * @return 返回media_id
     */
    public static String uploadTemporaryMediaResource(MediaResourceRequest mediaResource, Function<String, String> accessTokenFun,
                                                      MediaCache mediaCacheRepository)
    {
        //尝试优先从缓存中获取
        String key = mediaResource.getResourceUrl().getPath();

        String mediaId = mediaCacheRepository.getMedia(key);

        if (!StringUtils.isEmpty(mediaId))
        {
            return mediaId;
        }

        synchronized (mediaResource.getResourceUrl().getPath())
        {

            mediaId = mediaCacheRepository.getMedia(key);

            if (!StringUtils.isEmpty(mediaId))
            {
                return mediaId;
            }

            try
            {
                FileUrlResource resource = new FileUrlResource(mediaResource.getResourceUrl());

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                headers.setConnection("Keep-Alive");
                headers.setCacheControl("no-cache");
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

                byte[] byteArray = IOUtils.toByteArray(mediaResource.getResourceUrl());
                body.add("file", byteArray);
                body.add("filelength", String.valueOf(byteArray.length));
                body.add("filename", FilenameUtils.getName(mediaResource.getResourceUrl().getPath()));
                body.add("content-type", "multipart/form-data");

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

                String appId = mediaResource.getAppId();

                String url = MessageUrl.UPLOAD_TEMP_MEDIA_URL + accessTokenFun.apply(appId) + "&type=" + mediaResource.getType();

                JSONObject jsonObject = extractResponse(REST_TEMPLATE.postForEntity(url, requestEntity, String.class));

                if (isSuccess(jsonObject))
                {
                    mediaId = jsonObject.getString("media_id");

                    //写入到缓存中
                    mediaCacheRepository.saveMedia(key, mediaId);

                    return mediaId;
                }

                throw new WxException(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));

            }
            catch (Exception e)
            {
                LOGGER.error("媒体资源上传错误", e);
                throw new RuntimeException("上传媒体资源失败");
            }
        }
    }


    /**
     * 创建永久的服务号二维码
     *
     * @param qrCode QrCode：二维码请求
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     *
     * @return 可直接访问的二维码地址
     */
    public static String createPermanentQrCodeWithOutCache(QrCode qrCode, Function<String, String> accessTokenFun)
    {
        QrCodeRequest qrCodeRequest = new QrCodeRequest(qrCode.getAppId(), qrCode.getSceneParam());

        return doCreateQrCodeWithoutCache(qrCodeRequest, accessTokenFun);
    }


    /**
     * 创建永久的服务号二维码，带缓存
     *
     * @param qrCode QrCode：二维码请求
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     *
     * @return 可直接访问的二维码地址
     */
    public static String createPermanentQrCode(QrCode qrCode, Function<String, String> accessTokenFun)
    {

        return createPermanentQrCode(qrCode, accessTokenFun, DEFAULT_QRCODE_CACHE);
    }


    /**
     * 创建永久的服务号二维码，可以自定义缓存实现
     *
     * @param qrCode QrCode：二维码请求
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     * @param qrCodeCacheRepository QrCodeCache：自定义的缓存实现
     *
     * @return 可直接访问的二维码地址
     */
    public static String createPermanentQrCode(QrCode qrCode, Function<String, String> accessTokenFun, QrCodeCache qrCodeCacheRepository)
    {

        QrCodeRequest qrCodeRequest = new QrCodeRequest(qrCode.getAppId(), qrCode.getSceneParam());

        return doCreateQrCode(qrCodeRequest, accessTokenFun, qrCodeCacheRepository);
    }


    /**
     * 创建临时的服务号二维码，不使用缓存
     *
     * @param qrCode QrCode：二维码请求
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     *
     * @return 可直接访问的二维码地址
     */
    public static String createTemporaryQrCodeWithOutCache(QrCode qrCode, Function<String, String> accessTokenFun)
    {
        TemporaryQrCodeRequest qrCodeRequest = new TemporaryQrCodeRequest(qrCode.getAppId(), qrCode.getSceneParam());

        return doCreateQrCodeWithoutCache(qrCodeRequest, accessTokenFun);
    }


    /**
     * 创建临时的服务号二维码，使用默认的内存缓存
     *
     * @param qrCode QrCode：二维码请求
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     *
     * @return 可直接访问的二维码地址
     */
    public static String createTemporaryQrCode(QrCode qrCode, Function<String, String> accessTokenFun)
    {
        return createTemporaryQrCode(qrCode, accessTokenFun, DEFAULT_QRCODE_CACHE);
    }


    /**
     * 创建临时的服务号二维码，可以自定义缓存实现
     *
     * @param qrCode QrCode：二维码请求
     * @param accessTokenFun Function<String, String>： 延迟计算获取accessToken的函数式方法
     * @param qrCodeCacheRepository QrCodeCache：自定义的缓存实现
     *
     * @return 可直接访问的二维码地址
     */
    public static String createTemporaryQrCode(QrCode qrCode, Function<String, String> accessTokenFun, QrCodeCache qrCodeCacheRepository)
    {
        TemporaryQrCodeRequest qrCodeRequest = new TemporaryQrCodeRequest(qrCode.getAppId(), qrCode.getSceneParam());

        return doCreateQrCode(qrCodeRequest, accessTokenFun, qrCodeCacheRepository);
    }


    private static String doCreateQrCode(QrCodeRequest qrCodeRequest, Function<String, String> accessTokenFun, QrCodeCache cacheRepository)
    {

        long sceneStrHash = qrCodeRequest.getActionInfo().getScene().getSceneStr().hashCode();

        String qrCodeUrl = cacheRepository.getQrCode(sceneStrHash);

        if (StringUtils.isNotEmpty(qrCodeUrl))
        {
            return qrCodeUrl;
        }

        synchronized (qrCodeRequest.getActionInfo().getScene().getSceneStr())
        {
            qrCodeUrl = cacheRepository.getQrCode(sceneStrHash);
            if (StringUtils.isNotEmpty(qrCodeUrl))
            {
                return qrCodeUrl;
            }
            else
            {
                String url = doCreateQrCodeWithoutCache(qrCodeRequest, accessTokenFun);

                saveToCache(qrCodeRequest, url, cacheRepository);

                return url;
            }
        }

    }


    private static String doCreateQrCodeWithoutCache(QrCodeRequest qrCodeRequest, Function<String, String> accessTokenFun)
    {
        String requestUrl = MessageUrl.CREATE_QRCODE_TICKET_URL + accessTokenFun.apply(qrCodeRequest.getAppId());

        HttpEntity<QrCodeRequest> httpEntity = new HttpEntity<>(qrCodeRequest, DEFAULT_HEADER);

        //拿Ticket
        JSONObject jsonObject = extractResponse(REST_TEMPLATE.postForEntity(requestUrl, httpEntity, String.class));

        if (!isSuccess(jsonObject))
        {
            throw new WxException(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
        }

        try
        {
            return MessageUrl.QRCODE_URL + URLEncoder.encode(jsonObject.getString("ticket"), "UTF-8");
        }
        catch (Exception e)
        {
            throw new RuntimeException("出现异常", e);
        }
    }


    /**
     * 创建小程序二维码
     *
     * @param qrCodeParam MiniProgramQrCodeParam：小程序二维码创建的参数值
     * @param accessToken String：微信服务器要求的accessToken
     *
     * @return 小程序二维码的字节数组
     */
    public static byte[] createMiniProgramQrCode(MiniProgramQrCodeParam qrCodeParam, String accessToken)
    {

        String requestUrl = MessageUrl.MINIPROGRAM_QRCODE_URL + accessToken;

        HttpEntity<Object> requestEntity = new HttpEntity<>(qrCodeParam, DEFAULT_HEADER);

        //小程序二维码特殊些，如果处理正确的话，返回的是byte数组
        ResponseEntity<byte[]> responseEntity = REST_TEMPLATE.postForEntity(requestUrl, requestEntity, byte[].class);

        if (responseEntity.getBody() == null)
        {
            throw new RuntimeException("小程序二维码生成失败");
        }

        String result = new String(responseEntity.getBody(), StandardCharsets.UTF_8);
        //请求失败
        if (result.contains("errcode"))
        {
            JSONObject jsonObject = JSON.parseObject(result);

            LOGGER.error("小程序二维码生成失败，错误原因：" + jsonObject.getString("errmsg"));

            throw new WxException(jsonObject.getInteger("errcode"), "小程序二维码生成失败" + jsonObject.getString("errmsg"));
        }

        return responseEntity.getBody();
    }


    /**
     * 创建服务号菜单
     *
     * @param menuButton MenuButton：服务号菜单数据
     *
     * @return 创建成功返回true，其他返回false
     */
    public static boolean createMenu(MenuButton menuButton)
    {
        try
        {
            HttpEntity<MenuButton> httpEntity = new HttpEntity<>(menuButton, DEFAULT_HEADER);
            JSONObject jsonObject = extractResponse(REST_TEMPLATE.postForEntity(menuButton.getUrl(), httpEntity, String.class));

            return isSuccess(jsonObject);
        }
        catch (RestClientException e)
        {
            LOGGER.error("菜单创建失败", e);
            return false;
        }
    }


    private static String saveToCache(QrCodeRequest qrCodeRequest, String qrCodeUrl, QrCodeCache qrCodeCacheRepository)
    {
        QrCodeCacheItem cacheItem;

        if (qrCodeRequest instanceof TemporaryQrCodeRequest)
        {
            cacheItem = new QrCodeCacheItem(qrCodeUrl, ((TemporaryQrCodeRequest)qrCodeRequest).getExpireSeconds());
        }
        else
        {
            cacheItem = new QrCodeCacheItem(qrCodeUrl);
        }

        long sceneStrHash = qrCodeRequest.getActionInfo().getScene().getSceneStr().hashCode();

        qrCodeCacheRepository.saveQrCode(sceneStrHash, cacheItem);

        return qrCodeUrl;
    }


    public static void main(String[] args) throws MalformedURLException
    {

        String wxGroupUrl = "http://fdfs.test.ximalaya.com/group1/M00/4D/39/wKgDplvOxoWAL562AACUsZTVKXE621.jpg";
        MediaResourceRequest resource = new MediaResourceRequest(MediaResourceTypeEnum.IMAGE, new URL(wxGroupUrl), "");

        String accessToken = "15_OP0OHMtWoc7Tr8DGbeFxaFLtj_iCucRE_mvlwKlk2qfr2jQm4eZxiKGL1yEuLLUm31FNBSDa6HxBnG7oFlFNnIrsILL5fNvMVo5L8CjLlzc_Y9k4rdX6avf4SiwFr5_NKXlyyX3b7Pua3lBsLXUdAAABNK";

        uploadTemporaryMediaResource(resource, obj -> accessToken);

        //testCreateMenu(accessToken);

    }


    //FIXME测试方法，将来要删除
    private static void testCreateMenu(String token)
    {
        MenuButton menuButton = new MenuButton(token);

        //一级菜单
        MenuButton.ButtonBean buttonBean1 = new MenuButton.ButtonBean();
        buttonBean1.setType("view");
        buttonBean1.setName("我要开课");
        buttonBean1.setUrl("https://daka.ximalaya.com/admin/user/login");

        MenuButton.ButtonBean buttonBean2 = new MenuButton.ButtonBean();
        buttonBean2.setType("miniprogram");
        buttonBean2.setName("今日打卡");
        buttonBean2.setAppId("wx1a95b0a2ef72071f");
        buttonBean2.setPagePath("pages/todayTask/todayTask");
        buttonBean2.setUrl("pages/todayTask/todayTask");

        MenuButton.ButtonBean buttonBean3 = new MenuButton.ButtonBean();
        buttonBean3.setType("click");
        buttonBean3.setName("客服");
        buttonBean3.setKey("qrCode");

        ImmutableList<MenuButton.ButtonBean> firstMenu = ImmutableList.of(buttonBean2, buttonBean1, buttonBean3);

        menuButton.setButtons(firstMenu);

        System.out.println("菜单创建结果" + createMenu(menuButton));
    }
}