package com.aaron.algorithm.binarysearch;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-02-14
 */
public class BinarySearchTreeTest
{
    public static void main(String[] args)
    {
        BinaryNode<Integer> rootNode = new BinaryNode<>(20);
        BinaryNode<Integer> node1 = new BinaryNode<>(10);
        rootNode.addLeftChild(node1);
        BinaryNode<Integer> node2 = new BinaryNode<>(50);
        rootNode.addRightChild(node2);

        BinaryNode<Integer> node3 = new BinaryNode<>(5);
        BinaryNode<Integer> node4 = new BinaryNode<>(15);
        node1.addLeftChild(node3);
        node1.addRightChild(node4);

        node4.addRightChild(new BinaryNode<>(18));

        BinaryNode<Integer> node5 = new BinaryNode<>(25);
        BinaryNode<Integer> node6 = new BinaryNode<>(75);
        node2.addLeftChild(node5);
        node2.addRightChild(node6);

        node6.addRightChild(new BinaryNode<>(100));

        BinaryNode<Integer> node7 = new BinaryNode<>(3);
        node3.addLeftChild(node7);
        node7.addLeftChild(new BinaryNode<>(2));

        BinarySearchTree<Integer> tree = new BinarySearchTree<>(rootNode);

        BinaryNode<Integer> binaryNode = tree.getBinaryNode(new BinaryNode<>(14));
        BinaryNode<Integer> binaryNode2 = tree.getBinaryNode(tree.getRoot(), new BinaryNode<>(20));
        binaryNode2.addRightChild(null);
        System.out.println(binaryNode);
        System.out.println(tree.findMin());
        System.out.println(tree.findMax());
        System.out.println(tree.contains(new BinaryNode<>(18)));
        System.out.println(tree.contains(tree.getRoot(), new BinaryNode<>(18)));

    }
}
