package com.java.basis.datastructure.linkedlist;

public class LinkedListExercise {
    public static void main(String[] args) {

    }

//    public ListNode reverseKGroup(ListNode head, int k) {
//
//        if (head.next == null || k <= 1) {
//            return head;
//        }
//        ListNode newHead = new ListNode(0);
//        newHead.next = head;
//
//        ListNode startNode = head;
//        ListNode endNode = null;
//
//        ListNode currentNode = head;
//
//        while (currentNode.next != null) {
//
//
//        }
//
//
//    }
//
//    public ListNode reverseNode(ListNode head) {
//        ListNode prevNode = null;
//        ListNode currentNode = head;
//
//        while (currentNode != null) {
//            ListNode nextNode = currentNode.next;
//            currentNode.next = prevNode;
//            prevNode = currentNode;
//            currentNode = nextNode;
//        }
//    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
