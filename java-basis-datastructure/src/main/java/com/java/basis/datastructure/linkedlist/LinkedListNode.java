package com.java.basis.datastructure.linkedlist;

public class LinkedListNode {
    public int getValue() {
        return value;
    }

    public LinkedListNode(int i)
    {
        this.value=i;
    }

    private int value;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedListNode) {
            LinkedListNode linkedListNode = (LinkedListNode) obj;
            if (this.getValue() == linkedListNode.getValue()) {
                return true;
            }
            return false;
        }
        return false;

    }

    @Override
    public String toString() {
        return "thisValue" + getValue();
    }
}
