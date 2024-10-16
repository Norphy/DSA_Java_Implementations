package com.omarorphy.data_structures;

import com.omarorphy.data_structures.interfaces.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoublyLinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    public void setup() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void checkIsEmpty() {
        Assertions.assertTrue(list.isEmpty());
        list.pushFront(5);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void checkGetSize() {
        Assertions.assertEquals(0, list.getSize());
        list.pushFront(5);
        Assertions.assertEquals(1, list.getSize());
        list.pushFront(5);
        Assertions.assertEquals(2, list.getSize());
        list.popBack();
        Assertions.assertEquals(1, list.getSize());
    }

    @Test
    public void checkValueAt() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.getValueAt(0));
        list.pushFront(5);
        Assertions.assertEquals((Integer) 5, list.getValueAt(0));
        list.pushFront(6);
        Assertions.assertEquals((Integer) 5, list.getValueAt(1));
        Assertions.assertEquals((Integer) 6, list.getValueAt(0));
        list.pushBack(7);
        Assertions.assertEquals((Integer) 7, list.getValueAt(2));
        Assertions.assertEquals((Integer) 5, list.getValueAt(1));
        Assertions.assertEquals((Integer) 6, list.getValueAt(0));
    }

    @Test
    public void checkPushFront() {
        list.pushFront(5);
        Assertions.assertEquals((Integer) 5, list.getValueAt(0));
        Assertions.assertEquals(1, list.getSize());
        list.pushFront(6);
        Assertions.assertEquals((Integer) 6, list.getValueAt(0));
        Assertions.assertEquals((Integer) 5, list.getValueAt(1));
        Assertions.assertEquals(2, list.getSize());
        list.popFront();
        list.popFront();
        list.pushFront(7);
        Assertions.assertEquals((Integer) 7, list.getValueAt(0));
        Assertions.assertEquals(1, list.getSize());
    }

    @Test
    public void checkPopFront() {

        Assertions.assertNull(list.popFront());
        list.pushFront(5);
        Assertions.assertEquals((Integer) 5, list.popFront());
        Assertions.assertEquals(0, list.getSize());
        list.pushFront(5);
        list.pushFront(6);
        Assertions.assertEquals((Integer) 6, list.popFront());
        Assertions.assertEquals(1, list.getSize());
        Assertions.assertEquals((Integer) 5, list.getValueAt(0));
        Assertions.assertEquals((Integer) 5, list.popFront());
        Assertions.assertEquals(0, list.getSize());
        Assertions.assertNull(list.popFront());
    }

    @Test
    public void checkPushBack() {
        list.pushBack(5);
        Assertions.assertEquals((Integer) 5, list.getValueAt(0));
        Assertions.assertEquals(1, list.getSize());
        list.pushBack(6);
        Assertions.assertEquals((Integer) 5, list.getValueAt(0));
        Assertions.assertEquals((Integer) 6, list.getValueAt(1));
        Assertions.assertEquals(2, list.getSize());
        list.popFront();
        Assertions.assertEquals((Integer) 6, list.getValueAt(0));
        list.popFront();
        list.pushBack(7);
        Assertions.assertEquals((Integer) 7, list.getValueAt(0));
        Assertions.assertEquals(1, list.getSize());
    }

    @Test
    public void checkPopBack() {

        Assertions.assertNull(list.popBack());
        list.pushFront(5);
        Assertions.assertEquals((Integer) 5, list.popBack());
        Assertions.assertEquals(0, list.getSize());
        list.pushFront(5);
        list.pushFront(6);
        Assertions.assertEquals((Integer) 5, list.popBack());
        Assertions.assertEquals(1, list.getSize());
        Assertions.assertEquals((Integer) 6, list.getValueAt(0));
        Assertions.assertEquals((Integer) 6, list.popBack());
        Assertions.assertEquals(0, list.getSize());
        Assertions.assertNull(list.popBack());
    }

    @Test
    public void checkFront() {

        Assertions.assertNull(list.back());
        list.pushFront(5);
        Assertions.assertEquals((Integer) 5, list.back());
        Assertions.assertEquals(1, list.getSize());
        list.pushFront(6);
        list.pushFront(7);
        Assertions.assertEquals((Integer) 5, list.back());
        Assertions.assertEquals(3, list.getSize());
        list.removeValue(5);
        Assertions.assertEquals(2, list.getSize());
        Assertions.assertEquals((Integer) 7, list.getValueAt(0));
        list.popFront();
        Assertions.assertEquals((Integer) 6, list.back());
        Assertions.assertEquals(1, list.getSize());
        list.popFront();
        Assertions.assertNull(list.back());
    }
}
