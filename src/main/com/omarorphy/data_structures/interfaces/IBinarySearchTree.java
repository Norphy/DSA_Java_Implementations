package com.omarorphy.data_structures.interfaces;

import com.omarorphy.data_structures.BinarySearchTree.BSTNode;

public interface IBinarySearchTree<V> {

    public boolean isEmpty();

    public void insertValue(V value);

    public void deleteValue(V value);

    public void deleteTree();

    public boolean isValueInTree(V value);

    public BSTNode<V> findNode(V value);

    public void printTree();

    public int getHeight();

    public V findMinValue();

    public V findMaxValue();

    public boolean isBinarySearchTree();

    public BSTNode<V> getSuccessor(V value);

    public BSTNode<V> getPredecessor(V value);



}
