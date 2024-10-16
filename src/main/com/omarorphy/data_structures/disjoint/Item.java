package com.omarorphy.data_structures.disjoint;

public class Item <T> {

    Item<T> parent;
    int size;
    T value;

    public Item(T value) {
        this.value = value;
        this.parent = this;
        this.size = 1;
    }
}
