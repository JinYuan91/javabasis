package com.java.basis.datastructure.slidingwindow;

import java.util.Arrays;

/**
 * @author ：无始
 * @date ：Created in 2021/1/13 2:08 下午
 * @description：滑动窗口算法实现
 * @modified By：
 * @version:
 */
public class SlidingWindow {

    public static void main(String[] args) {
        int[] params = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        SlidingWindow slidingWindow = new SlidingWindow();
        int[] result = slidingWindow.maxSlidingWindow(params, 3);
        System.out.println(result[0]);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        if (nums.length == k) {
            int[] result = new int[1];
            result[0] = Arrays.stream(nums).max().getAsInt();
            return result;
        }
        DecreasingQueue decreasingQueue = new DecreasingQueue(k);
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            decreasingQueue.push(i, nums[i]);
            if (i >= k - 1) {
                result[resultIndex] = decreasingQueue.getMaxValue();
                resultIndex++;
            }
        }
        return result;
    }
}

/**
* @Description: 单调队列 (双向链表)
* @Param:  * @param null
* @return:
* @Author: 无始
* @Date: 2021/1/13+5:44 下午
*/
class DecreasingQueue {
    private DecreasingQueueNode head;
    private DecreasingQueueNode tail;
    private int k;
    private int maxValue = 0;

    public DecreasingQueue(int k) {
        this.k = k;
    }

    public void push(int index, int value) {
        DecreasingQueueNode decreasingQueueNode = new DecreasingQueueNode(index, value);
        if (head == null) {//首次插入
            head = decreasingQueueNode;
            tail = decreasingQueueNode;
            maxValue = value;
            return;
        }
        if (tail.getValue() > value) {//如果队尾value大于当前新增节点value 则新增
            decreasingQueueNode.setPre(tail);
            tail.setNext(decreasingQueueNode);
            tail = decreasingQueueNode;
        } else {
            if (head == tail) {//当前队列只有一个节点
                head = decreasingQueueNode;
                tail = decreasingQueueNode;
                maxValue = value;
                return;
            }
            DecreasingQueueNode condition = tail.getPre();//向队尾节点前置节点比较
            do {
                if (condition.getValue() > value) {//如果前置节点value大于当前新增节点value 则新增 并删除队尾
                    decreasingQueueNode.setPre(condition);
                    condition.setNext(decreasingQueueNode);
                    tail = decreasingQueueNode;
                    break;
                } else {
                    if (condition == head) {//如果前置节点是队列head节点 则清空队列 设置新增节点为head tail节点
                        head = decreasingQueueNode;
                        tail = decreasingQueueNode;
                        maxValue = value;
                        return;
                    }
                }
                if (condition.getPre() != null) {
                    condition = condition.getPre();
                } else {
                    break;
                }
            } while (condition.getPre() != null || condition == head);
        }

        //在新增节点不是head节点情况下 判断删除队列head结点 判断head节点index是否在窗口内 如果不在则删除
        if (head != decreasingQueueNode) {
            if (head.getIndex() < (index - k + 1)) {
                head = head.getNext();
                head.setPre(null);
                maxValue = head.getValue();
            }
        }

    }
    public int getMaxValue() {
        return maxValue;
    }
}

class DecreasingQueueNode {
    private int index;
    private int value;
    private DecreasingQueueNode next;
    private DecreasingQueueNode pre;

    public DecreasingQueueNode(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DecreasingQueueNode getNext() {
        return next;
    }

    public void setNext(DecreasingQueueNode next) {
        this.next = next;
    }

    public DecreasingQueueNode getPre() {
        return pre;
    }

    public void setPre(DecreasingQueueNode pre) {
        this.pre = pre;
    }
}
