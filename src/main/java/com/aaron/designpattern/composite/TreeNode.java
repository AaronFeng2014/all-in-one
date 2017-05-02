package com.aaron.designpattern.composite;

import java.util.Enumeration;
import java.util.Vector;

public class TreeNode
{

    private String name;
    private TreeNode parent;


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public TreeNode getParent()
    {
        return parent;
    }


    public void setParent(TreeNode parent)
    {
        this.parent = parent;
    }


    private Vector<TreeNode> children = new Vector<TreeNode>();


    public TreeNode(String name)
    {
        this.name = name;
    }


    // 添加孩子节点
    public void add(TreeNode node)
    {
        children.add(node);
    }


    // 山粗孩子节点
    public void remove(TreeNode node)
    {
        children.remove(node);
    }


    //取得孩子节点
    public Enumeration<TreeNode> getChildren()
    {
        return children.elements();
    }
}
