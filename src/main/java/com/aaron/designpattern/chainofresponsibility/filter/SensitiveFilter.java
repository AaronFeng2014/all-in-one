package com.aaron.designpattern.chainofresponsibility.filter;

import com.aaron.designpattern.chainofresponsibility.FilterChain;
import com.aaron.designpattern.chainofresponsibility.Request;
import com.aaron.designpattern.chainofresponsibility.Response;

public class SensitiveFilter implements Filter
{

    @Override
    public void doFilter(Request request, Response response, FilterChain fc)
    {
        request.requestStr = request.requestStr.replace("fuck", "*") + "----SensitiveFilter";
        if (!request.requestStr.contains("FaceFilter"))
        {

            fc.doFilter(request, response, fc);
            response.responseStr += "----SensitiveFilter";
        }
        else
        {
            throw new RuntimeException("FaceFilter----SensitiveFilter");
        }
    }

}
