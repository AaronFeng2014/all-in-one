package com.aaron.designpattern.chainofresponsibility;

import com.aaron.designpattern.chainofresponsibility.filter.Filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter
{
    List<Filter> filters = new ArrayList<>();
    int index = 0;


    public FilterChain addFilter(Filter filter)
    {
        this.filters.add(filter);
        return this;
    }


    @Override
    public void doFilter(Request request, Response response, FilterChain fc)
    {
        // TODO Auto-generated method stub
        if (index == filters.size())
        {
            System.out.print(request.requestStr);
            return;
        }
        Filter filter = filters.get(index++);
        filter.doFilter(request, response, fc);

    }
}
