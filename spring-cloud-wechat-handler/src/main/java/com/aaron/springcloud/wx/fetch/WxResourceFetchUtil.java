package com.aaron.springcloud.wx.fetch;

import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.domain.QrCodeRequest;
import com.aaron.springcloud.wx.domain.TemporaryQrCodeRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate = new RestTemplate();


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
     * 创建永久的服务号二维码
     *
     * @param sceneParam String
     * @param accessToken String：微信服务器要求的accessToken
     * @return 可直接访问的二维码地址
     */
    public String createPermanentQrCode(String sceneParam, String accessToken)
    {

        QrCodeRequest qrCodeRequest = new QrCodeRequest(sceneParam);

        return doCreateQrCode(qrCodeRequest, accessToken);
    }


    /**
     * 创建临时的服务号二维码
     *
     * @param sceneParam String
     * @param accessToken String：微信服务器要求的accessToken
     * @return 可直接访问的二维码地址
     */
    public String createTemporaryQrCode(String sceneParam, String accessToken)
    {
        QrCodeRequest qrCodeRequest = new TemporaryQrCodeRequest(sceneParam);

        return doCreateQrCode(qrCodeRequest, accessToken);
    }


    private String doCreateQrCode(QrCodeRequest qrCodeRequest, String accessToken)
    {
        String requestUrl = MessageUrl.CREATE_QRCODE_TICKET_URL + accessToken;

        //拿Ticket
        JSONObject jsonObject = extractResponse(restTemplate.postForEntity(requestUrl, JSON.toJSONString(qrCodeRequest), String.class));

        if (isSuccess(jsonObject))
        {
            try
            {
                return MessageUrl.QRCODE_URL + URLEncoder.encode(jsonObject.getString("ticket"), "UTF-8");
            }
            catch (Exception e)
            {
                throw new RuntimeException("编码异常", e);
            }
        }

        throw new RuntimeException("生成获取二维码的ticket失败");
    }


    /**
     * 创建小程序二维码
     *
     * @param sceneParam String
     * @param accessToken String：微信服务器要求的accessToken
     * @return 可直接访问的二维码地址
     */
    public String createMiniProgramQrCode(String sceneParam, String accessToken)
    {

        //MessageUrl.CREATE_QRCODE_TICKET_URL;

        //restTemplate

        return "";
    }


    private JSONObject extractResponse(ResponseEntity<String> responseEntity)
    {

        if (!HttpStatus.OK.equals(responseEntity.getStatusCode()))
        {
            return null;
        }

        String entityBody = responseEntity.getBody();

        LOGGER.info("微信返回的结果是：{}", entityBody);

        return JSON.parseObject(entityBody);
    }


    private boolean isSuccess(JSONObject jsonObject)
    {

        if (jsonObject == null)
        {
            return false;
        }

        //微信处理成功标志
        boolean success = !jsonObject.containsKey("errcode") || jsonObject.getLong("errcode") != 0L;

        if (!success)
        {
            LOGGER.error("请求微信资源错误：{}", jsonObject.getString("errmsg"));

            return false;
        }

        return true;
    }
}