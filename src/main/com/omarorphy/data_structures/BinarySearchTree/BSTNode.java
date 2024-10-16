package com.omarorphy.data_structures.BinarySearchTree;

public class BSTNode <V> {
    public V value;
    public BSTNode<V> left;
    public BSTNode<V> right;

    public BSTNode(V value, BSTNode<V> left, BSTNode<V> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
