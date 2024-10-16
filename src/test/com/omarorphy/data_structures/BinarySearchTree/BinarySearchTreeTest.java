package com.omarorphy.data_structures.BinarySearchTree;

import com.omarorphy.data_structures.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> intBinarySearchTree;

    @BeforeEach
    public void setup() {
        this.intBinarySearchTree = new BinarySearchTree<>();
    }

    @Test
    public void isEmpty_Test() {
        assertTrue(this.intBinarySearchTree.isEmpty());
        this.intBinarySearchTree.insertValue(2);
        assertFalse(this.intBinarySearchTree.isEmpty());
    }

    @Test
    public void valueComparableOrContainsComparator_Test() {
        BinarySearchTree<BSTNode<?>> boolBinarySearchTree = new BinarySearchTree<>();
        boolBinarySearchTree.insertValue(new BSTNode<>(null, null, null));
        assertThrows(ClassCastException.class, () -> boolBinarySearchTree.insertValue(new BSTNode<>(null, null, null)));
    }

    @Test
    public void insertValue_Test() {

        assertFalse(this.intBinarySearchTree.isValueInTree(3));
        assertNull(this.intBinarySearchTree.getPredecessor(3));
        assertNull(this.intBinarySearchTree.getSuccessor(3));

        this.intBinarySearchTree.insertValue(3);
        assertTrue(this.intBinarySearchTree.isValueInTree(3));

        this.intBinarySearchTree.insertValue(4);
        assertTrue(this.intBinarySearchTree.isValueInTree(4));

        this.intBinarySearchTree.insertValue(2);
        assertTrue(this.intBinarySearchTree.isValueInTree(2));

        assertEquals(2, this.intBinarySearchTree.getPredecessor(3).value);
        assertEquals(4, this.intBinarySearchTree.getSuccessor(3).value);
    }

    @Test
    public void deleteValue_Test() {

        assertFalse(this.intBinarySearchTree.isValueInTree(3));
        this.intBinarySearchTree.insertValue(3);
        assertTrue(this.intBinarySearchTree.isValueInTree(3));
        this.intBinarySearchTree.insertValue(2);
        this.intBinarySearchTree.insertValue(4);

        //Node with two children
        this.intBinarySearchTree.deleteValue(3);
        assertFalse(this.intBinarySearchTree.isValueInTree(3));

        this.intBinarySearchTree.insertValue(3);

        //Node with single child
        this.intBinarySearchTree.deleteValue(4);
        assertFalse(this.intBinarySearchTree.isValueInTree(4));

        assertTrue(this.intBinarySearchTree.isValueInTree(3));
        //Leaf Node
        this.intBinarySearchTree.deleteValue(3);
        assertFalse(this.intBinarySearchTree.isValueInTree(3));
    }

    @Test
    public void deleteTree_Tree() {
        assertTrue(this.intBinarySearchTree.isEmpty());

        this.intBinarySearchTree.insertValue(3);

        assertFalse(this.intBinarySearchTree.isEmpty());

        this.intBinarySearchTree.deleteTree();

        assertTrue(this.intBinarySearchTree.isEmpty());
    }
}