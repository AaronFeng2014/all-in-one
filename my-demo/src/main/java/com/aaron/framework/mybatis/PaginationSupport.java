package com.aaron.framework.mybatis;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-23
 */
@Setter
@Getter
public class PaginationSupport<T> implements Serializable
{
    private long totalSize;

    private int currentPage;

    private int pageSize;

    private List<T> content;


    public PaginationSupport()
    {
        this(20);
    }


    public PaginationSupport(int pageSize)
    {
        this(1, pageSize);
    }


    public PaginationSupport(int currentPage, int pageSize)
    {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
