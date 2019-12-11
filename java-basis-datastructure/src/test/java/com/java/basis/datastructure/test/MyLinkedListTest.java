package com.java.basis.datastructure.test;

import com.java.basis.datastructure.linkedlist.LinkedListNode;
import com.java.basis.datastructure.linkedlist.MyLinkedList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
public class MyLinkedListTest {


    @Test
    public void linkedListBasisAddTest() {

        MyLinkedList<LinkedListNode> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            LinkedListNode linkedListNode = new LinkedListNode(i);
            myLinkedList.addFirstNode(linkedListNode);

        }
        try {
            Assert.assertThat(0, is(myLinkedList.getPopEndNode().getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 10; i < 20; i++) {
            LinkedListNode linkedListNode = new LinkedListNode(i);
            myLinkedList.addEndNode(linkedListNode);

        }
        try {
            Assert.assertThat(19, is(myLinkedList.getPopEndNode().getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void linkedListRemoveTest() {
        MyLinkedList<LinkedListNode> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            LinkedListNode linkedListNode = new LinkedListNode(i);
            myLinkedList.addFirstNode(linkedListNode);

        }
        try {
            Assert.assertThat(0, is(myLinkedList.getPopEndNode().getValue()));
            Assert.assertThat(1, is(myLinkedList.getPopEndNode().getValue()));
            Assert.assertThat(9, is(myLinkedList.getPopFirstNode().getValue()));
            Assert.assertThat(8, is(myLinkedList.getPopFirstNode().getValue()));
            Assert.assertThat(6, is(myLinkedList.getLen()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
