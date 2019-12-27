package com.java.basis.datastructure.stack;

import java.util.Stack;

/**
* @Description:230 二叉搜索树中第K小的元素
* @Param:
* @return:
* @Author: 无始
* @Date: 2019/12/27+6:30 PM
*/
public class QueryTreeExercise {

    private int index=0;
    public int kthSmallest(TreeNode root, int k) {
        int[] nums=new int[k];

        d(root,nums);
        return nums[k-1];
    }

    private void d(TreeNode node,int[] nums)
    {
        if(node.getLeft()!=null)
        {
            d(node.getLeft(),nums);
        }
        if(index<nums.length) {
            nums[index] = node.getVal();
            index++;
        }
        else
        {
            return;
        }
        if(node.getRight()!=null)
        {
            d(node.getRight(),nums);
        }
    }
}


class TreeNode {
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    int val;
      TreeNode left;
      TreeNode right;
     TreeNode(int x) { val = x; }
 }

