package com.aaron.designpattern.chainofresponsibility.filter;

import com.aaron.designpattern.chainofresponsibility.FilterChain;
import com.aaron.designpattern.chainofresponsibility.Request;
import com.aaron.designpattern.chainofresponsibility.Response;

public interface Filter
{
    void doFilter(Request request, Response response, FilterChain fc);
}
