package com.aaron.util.http;

import com.aaron.util.StringUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HttpUtil
{

    private static final Log LOG = LogFactory.getLog(HttpUtil.class);

    private static final int TIME_OUT_SECONDS = 60000;

    /**
     * https请求路径前缀
     */
    private static final String HTTPS_PREFIX = "https://";

    /**
     * 默认content-type
     */
    private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";


    /**
     * 该接口以http方式执行请求，参数以键值对的形式传递
     * Apache http Client访问，并获取结果
     *
     * @param param Map<String, String>：请求参数键值对
     * @param url String：请求地址
     * @return HttpResponseContent：http请求返回对象，该对象封装了请求状态码和返回内容
     */
    public static HttpResponseContent submitRequest(Map<String, String> param, String url)
    {

        HttpResponseContent response = new HttpResponseContent();
        if (StringUtil.isEmpty(url))
        {
            response.setResponse("请求路径不合法");
            return response;
        }

        HttpEntity entity = getEntity(param);

        return executeRequest(entity, url, "application/x-www-form-urlencoded", isHttps(url));

    }


    /**
     * 该接口以http方式执行请求，参数以流的形式传递
     * Apache http Client访问，并获取结果
     *
     * @param param String：请求参数,字符串形式
     * @param url String：请求地址
     * @return HttpResponseContent：http请求返回对象，该对象封装了请求状态码和返回内容
     */
    public static HttpResponseContent submitRequest(String param, String url)
    {
        HttpResponseContent response = new HttpResponseContent();
        if (StringUtil.isEmpty(url))
        {
            response.setResponse("请求路径不合法");
            return response;
        }

        HttpEntity entity = getEntity(param);

        return executeRequest(entity, url, CONTENT_TYPE, isHttps(url));

    }


    private static HttpResponseContent executeRequest(HttpEntity httpEntity, String url, String contextType, boolean isHttps)
    {
        CloseableHttpClient httpClient = getHttpClient(isHttps);

        HttpResponseContent responseContent = new HttpResponseContent();
        if (httpClient == null)
        {
            responseContent.setResponse("发起对" + url + "的请求失败，获取HttpClient为空");
            return responseContent;
        }

        CloseableHttpResponse httpResponse;
        try
        {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(TIME_OUT_SECONDS).setConnectionRequestTimeout(TIME_OUT_SECONDS)
                    .setSocketTimeout(TIME_OUT_SECONDS).build();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(config);
            httpPost.setHeader("Content-Type", contextType);
            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            responseContent.setStatus(statusCode);
            if (statusCode != HttpStatus.SC_OK)
            {
                LOG.error("请求连接失败,状态码statusCode=" + statusCode);
                responseContent.setResponse("请求连接失败,状态码statusCode=" + statusCode);
                return responseContent;
            }
            try
            {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null)
                {
                    responseContent.setResponse(EntityUtils.toString(entity, "UTF-8"));
                    return responseContent;
                }

            }
            catch (Exception e)
            {
                responseContent.setResponse(e.getMessage());
                LOG.error("http请求异常", e);
            }
            finally
            {
                close(httpResponse);
            }
        }
        catch (Exception e)
        {
            responseContent.setResponse(e.getMessage());
            LOG.error("http请求异常", e);
        }
        finally
        {
            close(httpClient);
        }

        return responseContent;
    }


    /**
     * 获取Https请求连接工厂
     *
     * @return SSLConnectionSocketFactory
     */
    private static SSLConnectionSocketFactory getSSLFactory()
    {
        try
        {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
            {
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException
                {
                    return true;
                }
            }).build();

            return new SSLConnectionSocketFactory(sslContext);
        }
        catch (Exception e)
        {
            LOG.error("构建SSL连接工厂失败");
            return null;
        }
    }


    /**
     * 判断是否是Https请求
     *
     * @param url String：请求路径
     * @return 如果是https请求返回true，否则返回false
     */
    private static boolean isHttps(String url)
    {
        return url.startsWith(HTTPS_PREFIX);
    }


    /**
     * 以键值对形式传递参数
     *
     * @param params Map<String, String>：参数键值对
     * @return HttpEntity：封装了参数的Entity对象
     */
    private static HttpEntity getEntity(Map<String, String> params)
    {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        try
        {
            if (MapUtils.isEmpty(params))
            {
                return new UrlEncodedFormEntity(paramList, "UTF-8");
            }
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries)
            {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                paramList.add(pair);
            }

            return new UrlEncodedFormEntity(paramList, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            LOG.error("编码不支持", e);
            return null;
        }
    }


    private static HttpEntity getEntity(String params)
    {
        return new StringEntity(params, "UTF-8");
    }


    /**
     * 根据传入的请求参数类型获取正确的请求方式
     *
     * @param isHttps boolean：发起的请求是否是https请求
     * @return CloseableHttpClient
     */
    private static CloseableHttpClient getHttpClient(boolean isHttps)
    {
        if (isHttps)
        {
            return HttpClients.custom().setSSLSocketFactory(getSSLFactory()).build();
        }
        else
        {
            return HttpClients.createDefault();
        }
    }


    private static void close(Closeable closeableObject)
    {
        if (closeableObject == null)
        {
            LOG.error("可关闭对象为空，closeableObject = null");
            return;
        }

        try
        {
            closeableObject.close();
        }
        catch (IOException e)
        {
            LOG.error("关闭发生异常", e);
        }
    }
}
