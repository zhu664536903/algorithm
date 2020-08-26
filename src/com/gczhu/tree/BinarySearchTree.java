package com.gczhu.tree;

import java.util.Random;

/**
 * 二叉查找树，特点：每个节点最多只有两个子节点，当前节点值总是大于左子节点值，总是小于右子节点值
 * 查询实现思路：
 *      如果查找值小于当前节点，在当前节点的左子树递归查找
 *      如果查找值大于当前节点，在当前节点的左子树递雪查找
 * 插入操作：
 *      如果插入值小于当前节点，当前节点左子树为空，直接插入，如果不为空是递归查找
 *      如果插入值大于当前节点，当前节点右子树为空时，直接插入，如果不为空递归查找
 *删除操作：
 *      1.如果要删除的节点没有子节点，将父节点指向该要删除节点的指针置null
 *      2.如果要删除的节点只有一个子节点，将被删除节点的父节点指向被删除节点的指针指向被删除节点的子节点
 *      3.如果被删除的节点有两个子节点，核心思想是找出大于左子树并且是右子树最小的节点，将满足上述条件的节点按照1，2情况删除后再插入被删除节点中
 *
 */
public class BinarySearchTree {
    private Node treeRoot;

    //查询指定数据
    public Node find(int data){
        Node p = treeRoot;

        while (p != null){
            if (data < p.data){
                p = p.left;
            }else if(data > p.data){
                p = p.right;
            }else{
                return p;
            }

        }
        return null;
    }

    //插入一个数据
    public void insert(int data){
        Node p = treeRoot;
        if (p == null){
            treeRoot = new Node(data);
            return;
        }
        while (p != null){
            if(data > p.data){
                if(p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }else if(data < p.data){
                    //插入值小于等于当前节点，选择当前节点左子树插入
                    if(p.left == null){
                        p.left = new Node(data);
                        return;
                    }
                    p = p.left;
            }else {
                System.out.println("插入了重复值"+data+",直接返回");
                return;
            }

        }
    }
    public void delete(int data){
        Node delNode = treeRoot;
        Node parentNode = null;

        //1.找出被删除节点以及其父节点
        while (delNode != null && delNode.data != data){
            parentNode = delNode;
            if (data > delNode.data) delNode = delNode.right;
            else delNode = delNode.left;
        }
        //2.要被删除的节点是否存在
        System.out.println(delNode);
        if (delNode == null) return;

        //3.如果被删除的节点存大两个子节点，找出右子树中最小的节点和其父节点
        if (delNode.left !=null && delNode.right != null){
            Node minNode = delNode.right;
            Node minParentNode = delNode;

            while (minNode.left != null){
                minParentNode = minNode;
                minNode = minNode.left;
            }
            delNode.data = minNode.data;//删除目标节点

            //最小节点成为被删除节点
            delNode = minNode;
            parentNode = minParentNode;
        }

        //4.找出被删除节点的子节点
        Node child = null;
        if (delNode.left !=null) child = delNode.left;
        else if (delNode.right != null) child = delNode.right;

        //5.将被删除节点的父节点指向被删除节点的指针指向被删除节点的子节点
        if (parentNode == null) treeRoot = null;
        else if (parentNode.left == delNode) parentNode.left = child;
        else if (parentNode.right == delNode) parentNode.right = child;

    }

    /**
     * 中序遍历
     */
    public void infixOrderPrint(){
        infixOrderPrintByNode(treeRoot);
    }

    public void infixOrderPrintByNode(Node node){
        if (node == null) return;
        //打印左边节点
        if(node.left != null){
            infixOrderPrintByNode(node.left);
        }
        //打印当前节点
        System.out.print(node.data+",");
        //打印右边节点
        if(node.right != null){
            infixOrderPrintByNode(node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < 20; i++) {
            binarySearchTree.insert(new Random().nextInt(100));
        }
//        binarySearchTree.insert(6);
//        binarySearchTree.insert(0);
//        binarySearchTree.insert(100);
//        binarySearchTree.insert(5);
//        binarySearchTree.insert(5);
//        binarySearchTree.insert(34);
//        binarySearchTree.insert(9);
//        binarySearchTree.insert(10);
//        binarySearchTree.insert(10);
        binarySearchTree.infixOrderPrint();
        System.out.println("删除10");
        binarySearchTree.delete(10);
        binarySearchTree.delete(11);
        binarySearchTree.delete(15);
        binarySearchTree.delete(6);

        binarySearchTree.infixOrderPrint();
    }


    static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
