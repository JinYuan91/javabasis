package com.java.basis.datastructure.tree;

import java.util.Objects;

public class Tree {
    private TreeNode root;

    public void addTreeNode(TreeNode treeNode) {
        if (Objects.equals(null, root)) {
            root = treeNode;
            return;
        }

        recursiveAddNode(root,treeNode);
    }

    private void recursiveAddNode(TreeNode treeNode,TreeNode value)
    {
        if(treeNode.getKey()>=value.getKey())
        {
            if(treeNode.hasRightChild())
            {
                recursiveAddNode(treeNode.getRightChild(),value);
            }
            else
            {
                treeNode.setRightChild(value);
            }
        }
        else
        {
            if(treeNode.hasLeftChild())
            {
                recursiveAddNode(treeNode.getLeftChild(),value);
            }
            else
            {
                treeNode.setLeftChild(value);
            }
        }
    }

}

class TreeNode {
    private TreeNode leftChild;
    private TreeNode rightChild;
    private int key;


    public boolean hasLeftChild() {
        return !Objects.equals(leftChild, null);
    }

    public boolean hasRightChild() {
        return !Objects.equals(rightChild, null);
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;


}
