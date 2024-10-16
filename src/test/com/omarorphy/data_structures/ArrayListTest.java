package com.omarorphy.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListTest {

    ArrayList<Integer> integerArrayList;
    ArrayList<String> stringArrayList;

    @BeforeEach
    public void setUp() {
        integerArrayList = new ArrayList<>();
        stringArrayList = new ArrayList<>();
    }

    @Test
    public void checkInitialSize() {
        assertArrayEmpty();
    }

    @Test
    public void checkAdd() {
        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";
        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);
        assertArraySize(1);
        Assertions.assertEquals(integerArrayList.get(0), valOne, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valOneString, "Array Value");
        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);
        assertArraySize(2);
        Assertions.assertEquals(integerArrayList.get(1), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.get(1), valTwoString, "Array Value");
    }

    @Test
    public void checkInsert() {

        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";
        Integer valThree = 5;
        String valThreeString = "5";
        Integer valFour = 6;
        String valFourString = "6";

        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);
        integerArrayList.insert(0, valTwo);
        stringArrayList.insert(0, valTwoString);

        assertArraySize(2);
        Assertions.assertEquals(integerArrayList.get(0), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valTwoString, "Array Value");
        Assertions.assertEquals(integerArrayList.get(1), valOne, "Array Value");
        Assertions.assertEquals(stringArrayList.get(1), valOneString,"Array Value");

        integerArrayList.insert(2, valThree);
        stringArrayList.insert(2, valThreeString);

        assertArraySize(3);
        Assertions.assertEquals(integerArrayList.get(0), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valTwoString, "Array Value");
        Assertions.assertEquals(integerArrayList.get(1), valOne, "Array Value");
        Assertions.assertEquals(stringArrayList.get(1), valOneString, "Array Value");
        Assertions.assertEquals(integerArrayList.get(2), valThree, "Array Value");
        Assertions.assertEquals(stringArrayList.get(2), valThreeString, "Array Value");

        integerArrayList.insert(1, valFour);
        stringArrayList.insert(1, valFourString);
        assertArraySize(4);
        Assertions.assertEquals(integerArrayList.get(0), valTwo, "Array Value");
        Assertions.assertEquals( stringArrayList.get(0), valTwoString, "Array Value");
        Assertions.assertEquals(integerArrayList.get(1), valFour, "Array Value");
        Assertions.assertEquals(stringArrayList.get(1), valFourString, "Array Value");
        Assertions.assertEquals(integerArrayList.get(2), valOne, "Array Value");
        Assertions.assertEquals(stringArrayList.get(2), valOneString, "Array Value");
    }

    @Test
    public void checkPrepend() {
        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";

        integerArrayList.prepend(valOne);
        stringArrayList.prepend(valOneString);
        assertArraySize(1);
        Assertions.assertEquals(integerArrayList.get(0), valOne, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valOneString, "Array Value");

        integerArrayList.prepend(valTwo);
        stringArrayList.prepend(valTwoString);
        assertArraySize(2);
        Assertions.assertEquals(integerArrayList.get(0), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valTwoString, "Array Value");
    }

    @Test
    public void checkPop() {
        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";
        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);
        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);

        Assertions.assertEquals(integerArrayList.pop(), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.pop(), valTwoString, "Array Value");
        assertArraySize(1);

        Assertions.assertEquals(integerArrayList.pop(), valOne, "Array Value");
        Assertions.assertEquals(stringArrayList.pop(), valOneString, "Array Value");
        assertArrayEmpty();

    }

    @Test
    public void checkDelete() {
        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";
        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);
        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);

        integerArrayList.delete(0);
        stringArrayList.delete(0);

        Assertions.assertEquals(integerArrayList.get(0), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valTwoString, "Array Value");
        assertArraySize(1);

        integerArrayList.delete(0);
        stringArrayList.delete(0);
        assertArrayEmpty();
    }

    @Test
    public void checkRemove() {
        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";
        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);
        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);
        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);



        Assertions.assertTrue(integerArrayList.remove(valOne), "Array Value Exists");
        Assertions.assertTrue(stringArrayList.remove(valOneString), "Array Value Exists");
        Assertions.assertEquals(integerArrayList.get(0), valTwo, "Array Value");
        Assertions.assertEquals(stringArrayList.get(0), valTwoString, "Array Value");
        assertArraySize(2);

        Assertions.assertTrue(integerArrayList.remove(valTwo), "Array Value Exists");
        Assertions.assertTrue(stringArrayList.remove(valTwoString), "Array Value Exists");
        assertArrayEmpty();
        Assertions.assertFalse(integerArrayList.remove(valTwo), "Array Value Exists");
        Assertions.assertFalse(stringArrayList.remove(valTwoString), "Array Value Exists");
    }

    @Test
    public void checkFind() {

        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";
        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);
        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);
        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);

        Assertions.assertEquals(integerArrayList.find(valOne), 0, "Array Value");
        Assertions.assertEquals(stringArrayList.find(valOneString), 0, "Array Value");
        integerArrayList.delete(0);
        stringArrayList.delete(0);
        Assertions.assertEquals(integerArrayList.find(valOne), 1, "Array Value");
        Assertions.assertEquals(stringArrayList.find(valOneString), 1, "Array Value");
        integerArrayList.delete(1);
        stringArrayList.delete(1);
        Assertions.assertEquals(integerArrayList.find(valOne), -1, "Array Value");
        Assertions.assertEquals(stringArrayList.find(valOneString), -1, "Array Value");
    }

    @Test
    public void checkToString() {

        Integer valOne = 3;
        String valOneString = "3";
        Integer valTwo = 4;
        String valTwoString = "4";

        Assertions.assertEquals(integerArrayList.toString(),"[]", "Array Value");
        Assertions.assertEquals(stringArrayList.toString(),"[]", "Array Value");

        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);

        Assertions.assertEquals(integerArrayList.toString(),"[3]", "Array Value");
        Assertions.assertEquals(stringArrayList.toString(),"[3]", "Array Value");

        integerArrayList.pushValue(valTwo);
        stringArrayList.pushValue(valTwoString);

        Assertions.assertEquals(integerArrayList.toString(),"[3, 4]", "Array Value");
        Assertions.assertEquals(stringArrayList.toString(),"[3, 4]", "Array Value");

        integerArrayList.pushValue(valOne);
        stringArrayList.pushValue(valOneString);

        Assertions.assertEquals(integerArrayList.toString(),"[3, 4, 3]", "Array Value");
        Assertions.assertEquals(stringArrayList.toString(),"[3, 4, 3]","Array Value");
    }

    private void assertArraySize(int expectedSize) {
        Assertions.assertTrue(stringArrayList.size() == expectedSize && !stringArrayList.isEmpty(), "String Array Size");
        Assertions.assertTrue(integerArrayList.size() == expectedSize && !integerArrayList.isEmpty(), "Integer Array Size");
    }

    private void assertArrayEmpty() {
        Assertions.assertTrue(stringArrayList.size() == 0 && stringArrayList.isEmpty(), "String Array Size");
        Assertions.assertTrue(integerArrayList.size() == 0 && integerArrayList.isEmpty(), "Integer Array Size");
    }
}
