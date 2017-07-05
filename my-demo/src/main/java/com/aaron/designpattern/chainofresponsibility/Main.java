package com.aaron.designpattern.chainofresponsibility;

import com.aaron.designpattern.chainofresponsibility.filter.FaceFilter;
import com.aaron.designpattern.chainofresponsibility.filter.HTMLFilter;
import com.aaron.designpattern.chainofresponsibility.filter.SensitiveFilter;

public class Main
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String msg = "大家好:),<script>,敏感,被就业,网络授课没感觉,因为做1爱看不到强1奸大家好";
        Request request = new Request();
        request.setRequestStr(msg);
        Response response = new Response();
        response.setResponseStr("");
        FilterChain fc = new FilterChain();
        FilterChain fc2 = new FilterChain();
        fc.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());
        fc2.addFilter(new FaceFilter());
        fc.addFilter(fc2);
        fc.doFilter(request, response, fc);
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
    }

}
