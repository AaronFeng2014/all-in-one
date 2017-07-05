package com.aaron.util.http;

/**
 * Http请求返回内容和状态码
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-10-12
 */
public class HttpResponseContent
{
    /**
     * 响应内容
     */
    private String response = "";

    /**
     * 请求状态码,初始化一个非200的数字
     */
    private int status = -1;


    public HttpResponseContent()
    {

    }


    public HttpResponseContent(int status)
    {
        this(status, "");

        this.status = status;

    }


    public HttpResponseContent(int status, String response)
    {
        this.status = status;
        this.response = response;
    }


    public String getResponse()
    {
        return response;
    }


    public void setResponse(String response)
    {
        this.response = response;
    }


    public int getStatus()
    {
        return status;
    }


    public void setStatus(int status)
    {
        this.status = status;
    }
}
