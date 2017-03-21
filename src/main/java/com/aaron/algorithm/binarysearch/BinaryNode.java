package com.aaron.algorithm.binarysearch;

/**
 * 二叉查找树的数据结构
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-02-14
 */
public class BinaryNode<T extends Comparable<T>>
{

    private T dataElement;

    private BinaryNode<T> left;

    private BinaryNode<T> right;

    private boolean isDelete;

    public static final int LEFT_CHILD = 0;

    public static final int RIGHT_CHILD = 1;


    BinaryNode(T dataElement)
    {
        this.dataElement = dataElement;
    }


    public BinaryNode(T dataElement, BinaryNode<T> left, BinaryNode<T> right)
    {
        this.dataElement = dataElement;
        this.left = left;
        this.right = right;
    }


    public void addChild(BinaryNode<T> node, int position)
    {
        if (node == null)
        {
            throw new IllegalArgumentException("参数错误");
        }

        if (LEFT_CHILD == position)
        {
            addLeftChild(node);
        }
        else if (RIGHT_CHILD == position)
        {
            addRightChild(node);
        }
    }


    void addLeftChild(BinaryNode<T> node)
    {
        this.left = node;
    }


    void addRightChild(BinaryNode<T> node)
    {
        this.right = node;
    }


    public void modifyData(T t)
    {
        this.dataElement = t;
    }


    BinaryNode<T> getLeft()
    {
        return left;
    }


    BinaryNode<T> getRight()
    {
        return right;
    }


    T getDataElement()
    {
        return dataElement;
    }


    public void setDelete(boolean delete)
    {
        isDelete = delete;
    }


    public boolean isDelete()
    {
        return isDelete;
    }
}
