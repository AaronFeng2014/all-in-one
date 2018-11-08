package com.aaron.springcloud.wx.util;

import com.aaron.springcloud.wx.cache.MediaCache;
import com.aaron.springcloud.wx.cache.QrCodeCache;
import com.aaron.springcloud.wx.cache.impl.MediaMemoryCacheRepository;
import com.aaron.springcloud.wx.cache.impl.QrCodeMemoryCacheRepository;
import com.aaron.springcloud.wx.constants.MediaResourceTypeEnum;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.domain.MediaResourceRequest;
import com.aaron.springcloud.wx.domain.MiniProgramQrCodeParam;
import com.aaron.springcloud.wx.domain.QrCode;
import com.aaron.springcloud.wx.domain.QrCodeCacheItem;
import com.aaron.springcloud.wx.domain.QrCodeRequest;
import com.aaron.springcloud.wx.domain.TemporaryQrCodeRequest;
import com.aaron.springcloud.wx.message.CostumerMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.ImmutableList;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

/**
 * 获取微信端资源工具类
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-07
 */
public final class WxResourceFetchUtil
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

    private static final RestTemplate REST_TEMPLATE;

    private static final HttpHeaders DEFAULT_HEADER;


    static
    {
        DEFAULT_HEADER = new HttpHeaders();
        //设置默认header
        DEFAULT_HEADER.setContentType(MediaType.APPLICATION_JSON);

        //设置 MessageConvert

        REST_TEMPLATE = new RestTemplate(ImmutableList.of(new FormHttpMessageConverter(), new FastJsonHttpMessageConverter()));
    }


    private WxResourceFetchUtil()
    {

    }


    /**
     * 获取AccessToken
     *
     * @return
     */
    private String getAccessToken()
    {
        return "";
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

        HttpEntity<CostumerMessage> requestEntity = new HttpEntity<>(costumerMessage);

        JSONObject jsonObject = extractResponse(REST_TEMPLATE.postForEntity(costumerMessage.getUrl(), requestEntity, String.class));

        return isSuccess(jsonObject);

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
    public static String uploadTemporaryMediaResource(MediaResourceRequest mediaResource,
                                                      Function<String, String> accessTokenFun,
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
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

                body.add("file", resource);
                body.add("filelength", "sdfsdg");
                body.add("content-type", "image");

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

            }
            catch (Exception e)
            {
                LOGGER.error("媒体资源上传错误", e);
            }
        }

        throw new RuntimeException("上传媒体资源失败");
    }


    /**
     * 创建永久的服务号二维码
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


    private static String doCreateQrCode(QrCodeRequest qrCodeRequest,
                                         Function<String, String> accessTokenFun,
                                         QrCodeCache qrCodeCacheRepository)
    {

        long sceneStrHash = qrCodeRequest.getActionInfo().getScene().getSceneStr().hashCode();

        String qrCodeUrl = qrCodeCacheRepository.getQrCode(sceneStrHash);

        if (StringUtils.isNotEmpty(qrCodeUrl))
        {
            return qrCodeUrl;
        }

        synchronized (qrCodeRequest.getActionInfo().getScene().getSceneStr())
        {
            if (StringUtils.isNotEmpty(qrCodeUrl))
            {
                return qrCodeUrl;
            }
            else
            {
                String requestUrl = MessageUrl.CREATE_QRCODE_TICKET_URL + accessTokenFun.apply(qrCodeRequest.getAppId());

                HttpEntity<QrCodeRequest> httpEntity = new HttpEntity<>(qrCodeRequest, DEFAULT_HEADER);

                //拿Ticket
                JSONObject jsonObject = extractResponse(REST_TEMPLATE.postForEntity(requestUrl, httpEntity, String.class));

                if (!isSuccess(jsonObject))
                {
                    throw new RuntimeException("生成获取二维码的ticket失败");
                }

                try
                {
                    return saveToCache(qrCodeRequest, jsonObject, qrCodeCacheRepository);
                }
                catch (Exception e)
                {
                    throw new RuntimeException("出现异常", e);
                }
            }
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

            throw new RuntimeException("小程序二维码生成失败：" + jsonObject.getString("errmsg"));
        }

        return responseEntity.getBody();
    }


    private static String saveToCache(QrCodeRequest qrCodeRequest, JSONObject jsonObject, QrCodeCache qrCodeCacheRepository)
            throws Exception
    {
        String qrCodeUrl = MessageUrl.QRCODE_URL + URLEncoder.encode(jsonObject.getString("ticket"), "UTF-8");

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


    private static JSONObject extractResponse(ResponseEntity<String> responseEntity)
    {

        if (!HttpStatus.OK.equals(responseEntity.getStatusCode()))
        {
            return null;
        }

        String entityBody = responseEntity.getBody();

        LOGGER.info("微信返回的结果是：{}", entityBody);

        return JSON.parseObject(entityBody);
    }


    private static boolean isSuccess(JSONObject jsonObject)
    {

        if (jsonObject == null)
        {
            return false;
        }

        //微信处理成功标志
        boolean success = !jsonObject.containsKey("errcode") || jsonObject.getLong("errcode") == 0L;

        if (!success)
        {
            LOGGER.error("请求微信资源错误：{}", jsonObject.getString("errmsg"));

            return false;
        }

        return true;
    }


    public static void main(String[] args) throws MalformedURLException
    {

        MediaResourceRequest resource = new MediaResourceRequest(MediaResourceTypeEnum.IMAGE,
                                                                 new URL("http://fdfs.test.ximalaya.com/group1/M00/4D/39/wKgDplvOxoWAL562AACUsZTVKXE621.jpg"),
                                                                 "");

        String accessToken = "15_OHpWm0zSxKWxxH4bORvNhusCNTxfaHnZOdLLYC8YUHSF3aWl2gVkMpmMm-23lif3wNTZ79v7Fw5nbTwi-6O8w_DTVU6rzf0uA1XMK_7BT6OqtbZ_ee9VKtmDH488CtJWQ4rmKMhOdIXH38HMGHQcAAAJKT";

        uploadTemporaryMediaResource(resource, obj -> accessToken);
    }
}