package com.omarorphy.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void checkInitialSize() {
        assertEmpty();
    }

    @Test
    public void checkValueAt() {
        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushBack(valOne);
        Assertions.assertEquals(list.getValueAt(0), valOne, "List Value");

        list.pushBack(valTwo);
        Assertions.assertEquals(list.getValueAt(1), valTwo, "List Value");

        list.pushBack(valThree);
        Assertions.assertEquals(list.getValueAt(2), valThree, "List Value");
        Assertions.assertEquals(list.getValueAt(1), valTwo, "List Value");
        Assertions.assertEquals(list.getValueAt(0), valOne, "List Value");
    }

    @Test
    public void checkPushFront() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushFront(valOne);
        Assertions.assertEquals(list.getValueAt(0), valOne, "List Value");
        assertSize(1);
        list.pushFront(valTwo);
        Assertions.assertEquals(list.getValueAt(1), valOne, "List Value");
        Assertions.assertEquals(list.getValueAt(0), valTwo, "List Value");
        assertSize(2);
        list.pushFront(valThree);
        Assertions.assertEquals(list.getValueAt(2), valOne, "List Value");
        Assertions.assertEquals(list.getValueAt(1), valTwo, "List Value");
        Assertions.assertEquals(list.getValueAt(0), valThree, "List Value");
        assertSize(3);
    }

    @Test
    public void checkPopFront() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushFront(valOne);
        list.pushFront(valTwo);
        list.pushFront(valThree);
        assertSize(3);

        Assertions.assertEquals(list.popFront(), valThree, "List Value");
        assertSize(2);

        Assertions.assertEquals(list.popFront(), valTwo, "List Value");
        assertSize(1);

        Assertions.assertEquals(list.popFront(), valOne, "List Value");
        assertEmpty();

        Assertions.assertNull(list.popFront(), "List Value");
    }

    @Test
    public void checkPopBack() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushFront(valOne);
        list.pushFront(valTwo);
        list.pushFront(valThree);
        assertSize(3);

        Assertions.assertEquals(list.popBack(), valOne, "List Value");
        assertSize(2);

        Assertions.assertEquals(list.popBack(), valTwo, "List Value");
        assertSize(1);

        Assertions.assertEquals(list.popBack(), valThree, "List Value");
        assertEmpty();

        Assertions.assertNull(list.popBack(), "List Value");
    }

    @Test
    public void checkFront() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushFront(valOne);
        list.pushFront(valTwo);
        list.pushFront(valThree);

        Assertions.assertEquals(list.front(), valThree, "List Value");
        assertSize(3);
        Assertions.assertEquals(list.popFront(), valThree, "List Value");
        assertSize(2);

        Assertions.assertEquals(list.front(), valTwo, "List Value");
        assertSize(2);
        Assertions.assertEquals(list.popFront(), valTwo, "List Value");
        assertSize(1);

        Assertions.assertEquals(list.front(), valOne,"List Value");
        assertSize(1);
        Assertions.assertEquals(list.popFront(), valOne, "List Value");
        assertEmpty();

        Assertions.assertNull(list.front(), "List Value");

    }

    @Test
    public void checkBack() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushFront(valOne);
        list.pushFront(valTwo);
        list.pushFront(valThree);
        assertSize(3);


        Assertions.assertEquals(list.back(), valOne, "List Value");
        assertSize(3);
        Assertions.assertEquals(list.popBack(), valOne, "List Value");
        assertSize(2);

        Assertions.assertEquals(list.back(), valTwo, "List Value");
        assertSize(2);
        Assertions.assertEquals(list.popBack(), valTwo, "List Value");
        assertSize(1);

        Assertions.assertEquals(list.back(), valThree, "List Value");
        assertSize(1);
        Assertions.assertEquals(list.popBack(), valThree, "List Value");
        assertEmpty();

        Assertions.assertNull(list.back(), "List Value");
    }

    @Test
    public void checkInsert() {
        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;
        Integer valFour = 6;

        list.insert(0, valOne);
        Assertions.assertEquals(valOne, list.getValueAt(0), "List Value");
        assertSize(1);
        list.insert(1, valTwo);
        Assertions.assertEquals(valOne, list.getValueAt(0), "List Value");
        Assertions.assertEquals(valTwo, list.getValueAt(1), "List Value");
        assertSize(2);
        list.insert(1, valThree);
        Assertions.assertEquals(valOne, list.getValueAt(0), "List Value");
        Assertions.assertEquals(valThree, list.getValueAt(1), "List Value");
        Assertions.assertEquals(valTwo, list.getValueAt(2), "List Value");
        assertSize(3);
        list.insert(0, valFour);
        Assertions.assertEquals(valFour, list.getValueAt(0), "List Value");
        Assertions.assertEquals(valOne, list.getValueAt(1), "List Value");
        Assertions.assertEquals(valThree, list.getValueAt(2), "List Value");
        Assertions.assertEquals(valTwo, list.getValueAt(3), "List Value");
        assertSize(4);
    }

    @Test
    public void checkErase() {
        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;
        Integer valFour = 6;

        list.pushBack(valOne);
        list.pushBack(valTwo);
        list.pushBack(valThree);
        list.pushBack(valFour);
        assertSize(4);

        list.erase(0);
        Assertions.assertEquals(valTwo, list.getValueAt(0), "List Value");
        Assertions.assertEquals(valThree, list.getValueAt(1), "List Value");
        Assertions.assertEquals(valFour, list.getValueAt(2), "List Value");
        assertSize(3);

        list.erase(1);
        Assertions.assertEquals(valTwo, list.getValueAt(0), "List Value");
        Assertions.assertEquals(valFour, list.getValueAt(1), "List Value");
        assertSize(2);

        list.erase(0);
        Assertions.assertEquals(valFour, list.getValueAt(0), "List Value");
        assertSize(1);

        list.erase(0);
        assertEmpty();
    }

    @Test
    public void checkValueFromNth() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;
        Integer valFour = 6;



        list.pushBack(valOne);
        list.pushBack(valTwo);
        list.pushBack(valThree);
        list.pushBack(valFour);
        Assertions.assertEquals(valFour, list.valueNFromEnd(0), "List Value");
        assertSize(4);


        Assertions.assertEquals(valFour, list.valueNFromEnd(0), "List Value");
        Assertions.assertEquals(valOne, list.valueNFromEnd(3), "List Value");
        Assertions.assertEquals(valThree, list.valueNFromEnd(1), "List Value");
        Assertions.assertEquals(valTwo, list.valueNFromEnd(2), "List Value");
        list.erase(2);
        Assertions.assertEquals(valOne, list.valueNFromEnd(2), "List Value");
    }

    @Test
    public void checkReverse() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushBack(valOne);
        list.reverse();
        Assertions.assertEquals(valOne, list.getValueAt(0), "List Value");

        list.pushBack(valTwo);
        list.reverse();

        Assertions.assertEquals(valOne, list.getValueAt(1), "List Value");
        Assertions.assertEquals(valTwo, list.getValueAt(0), "List Value");
        list.pushBack(valThree);
        list.reverse();
        Assertions.assertEquals(valOne, list.getValueAt(1), "List Value");
        Assertions.assertEquals(valTwo, list.getValueAt(2), "List Value");
        Assertions.assertEquals(valThree, list.getValueAt(0), "List Value");
        assertSize(3);
    }

    @Test
    public void checkRemoveValue() {

        Integer valOne = 3;
        Integer valTwo = 4;
        Integer valThree = 5;

        list.pushBack(valOne);
        list.pushBack(valTwo);
        list.pushBack(valThree);

        list.removeValue(valTwo);
        Assertions.assertEquals(valOne, list.getValueAt(0), "List Value");
        Assertions.assertEquals(valThree, list.getValueAt(1), "List Value");
        list.pushBack(valThree);

        list.removeValue(valThree);
        Assertions.assertEquals(valOne, list.getValueAt(0), "List Value");
        System.out.println("List: " + list);

        list.pushBack(valOne);
        list.pushBack(valTwo);
        list.removeValue(valOne);
        Assertions.assertEquals(valThree, list.getValueAt(0), "List Value");
        assertSize(3);
    }
    private void assertEmpty() {
        assertSize(0);
    }

    private void assertSize(int size) {
        Assertions.assertEquals(list.getSize(), size, "LinkedList Size");

        if(size == 0) Assertions.assertTrue(list.isEmpty(), "LinkedList Size");
        else Assertions.assertFalse(list.isEmpty(), "LinkedList Size");
    }
}
