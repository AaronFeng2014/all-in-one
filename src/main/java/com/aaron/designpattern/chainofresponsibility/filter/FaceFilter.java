package com.aaron.designpattern.chainofresponsibility.filter;

import com.aaron.designpattern.chainofresponsibility.FilterChain;
import com.aaron.designpattern.chainofresponsibility.Request;
import com.aaron.designpattern.chainofresponsibility.Response;

public class FaceFilter implements Filter
{

    @Override
    public void doFilter(Request request, Response response, FilterChain fc)
    {
        request.requestStr = request.requestStr.replace(":)", "^_^") + "----FaceFilter";
        if (!request.requestStr.contains("ee"))
        {

            fc.doFilter(request, response, fc);
            response.responseStr += "----FaceFilter";
        }
        else
        {
            throw new RuntimeException("FaceFilter----FaceFilter");
        }
    }

}
