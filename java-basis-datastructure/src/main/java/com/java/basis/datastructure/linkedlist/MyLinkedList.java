package com.java.basis.datastructure.linkedlist;

import java.util.Objects;

/**
 * @Description:customize linkedlist  non-thread-safe
 * @Author: 无始
 * @Date: 2019/12/10+2:51 PM
 */
public class MyLinkedList<E> {

    private Node<E> head;

    private Node<E> end;

    /**
     * @Description:
     * @Author: 无始
     * @Date: 2019/12/10+2:51 PM
     */
    public void addFirstNode(E e) {
        final Node<E> f = head;

        final Node<E> newNode = new Node<>(e);
        if (f == null) {
            head = newNode;
            end = newNode;
            return;
        }
        newNode.setNext(f);
        head = newNode;
        return;
    }

    /**
     * @Description:
     * @Author: 无始
     * @Date: 2019/12/10+2:51 PM
     */
    public void addEndNode(E e) {
        final Node<E> endNode = end;
        final Node<E> newNode = new Node<>(e);
        if (endNode == null) {
            addFirstNode(e);
            return;
        }
        endNode.setNext(newNode);
        end = newNode;
        return;

    }
    /**
     * @Description:
     * @Author: 无始
     * @Date: 2019/12/10+4:27 PM
     */
    public boolean removeNode(E e) {
        Node<E> prevNode = null;
        Node<E> currnetNode = head;
        while (currnetNode != null) {
            if (Objects.equals(currnetNode.getE(), e))//判断头节点
            {
                //头节点处理
                if (prevNode == null) {
                    head = currnetNode.getNext();
                    currnetNode = null;
                    return true;
                }

                //尾节点处理
                if (currnetNode.getNext() == null) {
                    prevNode.setNext(null);
                    end = prevNode;
                    currnetNode = null;
                    return true;
                }

                prevNode.setNext(currnetNode.getNext());
                currnetNode = null;
                return true;
            }
            //平移
            prevNode = currnetNode;
            currnetNode = currnetNode.getNext();

        }
        return false;
    }
    /**
     * @Description: pop firstnode
     * @Author: 无始
     * @Date: 2019/12/11+11:14 AM
     */
    public E getPopFirstNode() throws Exception {
        final Node<E> firstNode = head;
        if (firstNode != null) {
            if (firstNode.getNext() == null) {
                head = null;
                end = null;
                return firstNode.getE();
            }
            head = firstNode.getNext();
            return firstNode.getE();
        } else {
            throw new Exception("check linkedlist len");
        }
    }
    /**
     * @Description: pop endnode
     * @Author: 无始
     * @Date: 2019/12/11+11:06 AM
     */
    public E getPopEndNode() throws Exception {
        final Node<E> endNode = end;
        final Node<E> firstNode = head;

        if (endNode != null) {
            if (Objects.equals(endNode.getE(), firstNode.getE())) {
                head = null;
                end = null;
                return endNode.getE();
            }
            Node<E> prev = null;
            Node<E> currentNode = head;
            while (prev == null) {
                if (Objects.equals(currentNode.getNext().getE(), endNode.getE())) {
                    prev = currentNode;
                }
                currentNode = currentNode.getNext();

            }

            prev.setNext(null);
            end = prev;
            return endNode.getE();


        } else {
            throw new Exception("check linkedlist len");
        }
    }

    /**
     * @Description: 获取链表长度
     * @Param:1
     * @return:
     * @Author: 无始
     * @Date: 2019/12/11+11:15 AM
     */
    public int getLen() {
        int len = 0;
        Node<E> currnetNode = head;
        while (currnetNode != null) {
            len++;
            currnetNode = currnetNode.getNext();

        }
        return len;
    }


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/10+4:35 PM
     */
    public void printLinkedList() {
        Node<E> currnetNode = head;
        while (currnetNode != null) {
            System.out.println(currnetNode.getE().toString());
            currnetNode = currnetNode.getNext();

        }
    }

}

class Node<E> {
    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    private Node<E> next;

    public E getE() {
        return e;
    }

    private E e;

    public Node(E e) {
        this.e = e;
    }
}
