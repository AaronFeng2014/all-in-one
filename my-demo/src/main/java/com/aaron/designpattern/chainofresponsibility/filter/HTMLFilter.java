package com.aaron.designpattern.chainofresponsibility.filter;

import com.aaron.designpattern.chainofresponsibility.FilterChain;
import com.aaron.designpattern.chainofresponsibility.Request;
import com.aaron.designpattern.chainofresponsibility.Response;

public class HTMLFilter implements Filter
{

    @Override
    public void doFilter(Request request, Response response, FilterChain fc)
    {
        request.requestStr = request.requestStr.replace("<", "(").replace(">", ")") + "----HTMLFilter";
        if (!request.requestStr.contains("aa"))
        {

            fc.doFilter(request, response, fc);
            response.responseStr += "----HTMLFilter";
        }
        else
        {
            throw new RuntimeException("----HTMLFilter");
        }
    }

}
