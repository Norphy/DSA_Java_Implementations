package com.omarorphy.data_structures.interfaces;

public interface  IUnionFind <T, V> {

    T find(T value);

    void union(T first, T second);

    void makeSet(V set);

    int getNumSets();
}
