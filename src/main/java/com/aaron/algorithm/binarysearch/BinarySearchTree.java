package com.aaron.algorithm.binarysearch;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-02-14
 */
class BinarySearchTree<T extends Comparable<T>>
{

    private BinaryNode<T> root;


    BinarySearchTree(BinaryNode<T> root)
    {
        this.root = root;
    }


    T findMin()
    {
        return findMin(this.root);
    }


    /**
     * 删除元素<br/>
     * 1. 如果节点是树叶（即没有左子节点和右子节点），则可以直接删除<br/>
     * 2. 如果节点有一个子节点（左子节点或者右子节点），则删除该节点后，直接把该节点的子节点替换该节点即可<br/>
     * 3. 如果节点有两个子节点（既有左子节点又有右子节点）<br/>
     *
     * @param node BinaryNode<T>：要删除的节点
     * @return boolean：是否成功删除，删除成功返回true，删除失败返回false，删除的节点不在树中也返回true
     */
    boolean removeNode(BinaryNode<T> node)
    {
        if (node == null)
        {
            throw new IllegalArgumentException("can not delete null element");
        }

        BinaryNode<T> compareNode = getBinaryNode(node);

        //要删除的节点不在树中
        if (compareNode == null)
        {
            return true;
        }

        if (compareNode.getLeft() == null && compareNode.getRight() != null)
        {
            return false;
        }
        else if (compareNode.getLeft() != null && compareNode.getRight() == null)
        {
            return false;
        }
        else if (compareNode.getLeft() != null && compareNode.getRight() != null)
        {
            //既有左子节点又有右子节点
            compareNode.setDelete(true);
            return false;
        }
        else if (compareNode.getLeft() == null && compareNode.getRight() == null)
        {
            return false;

        }

        return false;
    }


    public BinaryNode<T> getBinaryNode(BinaryNode<T> node)
    {
        BinaryNode<T> compareNode = this.root;
        int compareResult = compare(compareNode, node);

        while (compareResult != 0)
        {
            if (compareResult < 0)
            {
                if (compareNode.getRight() == null)
                {
                    compareNode = null;
                    break;
                }

                compareResult = compare(compareNode.getRight(), node);
                compareNode = compareNode.getRight();
            }
            else if (compareResult > 0)
            {
                if (compareNode.getLeft() == null)
                {
                    compareNode = null;
                    break;
                }

                compareResult = compare(compareNode.getLeft(), node);
                compareNode = compareNode.getLeft();
            }

        }
        return compareNode;
    }


    public BinaryNode<T> getBinaryNode(BinaryNode<T> sourceNode, BinaryNode<T> node)
    {
        int compareResult = compare(sourceNode, node);

        if (compareResult < 0)
        {
            if (sourceNode.getRight() == null)
            {
                return null;
            }
            return getBinaryNode(sourceNode.getRight(), node);
        }
        else if (compareResult > 0)
        {
            if (sourceNode.getLeft() == null)
            {
                return null;
            }
            return getBinaryNode(sourceNode.getLeft(), node);
        }

        return sourceNode;
    }


    private T findMin(BinaryNode<T> node)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getLeft() == null)
        {
            return node.getDataElement();
        }

        return findMin(node.getLeft());
    }


    T findMax()
    {
        return findMax(this.root);
    }


    private T findMax(BinaryNode<T> node)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getRight() == null)
        {
            return node.getDataElement();
        }

        return findMax(node.getRight());
    }


    boolean contains(BinaryNode<T> node)
    {
        return node != null && contains(this.root, node);
    }


    /**
     * 不用担心栈空间不够，时间复杂度很低
     *
     * @param source
     * @param node
     * @return
     */
    boolean contains(BinaryNode<T> source, BinaryNode<T> node)
    {
        if (source == null || node == null)
        {
            return false;
        }

        int compareResult = compare(source, node);

        if (compareResult == 0)
        {
            return true;
        }

        if (compareResult > 0)
        {
            return contains(source.getLeft(), node);
        }
        else
        {
            return contains(source.getRight(), node);
        }
    }


    private int compare(BinaryNode<T> sourceNode, BinaryNode<T> targetNode)
    {
        return sourceNode.getDataElement().compareTo(targetNode.getDataElement());
    }


    BinaryNode<T> getRoot()
    {
        return root;
    }
}
