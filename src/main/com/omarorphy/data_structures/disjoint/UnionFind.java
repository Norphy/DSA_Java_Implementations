package com.omarorphy.data_structures.disjoint;

import com.omarorphy.data_structures.interfaces.IUnionFind;

public class UnionFind <T> implements IUnionFind<Item<T>, T> {

    private int numSets;

    public UnionFind() {
        numSets = 0;
    }

    @Override
    public Item<T> find(Item<T> item) {
        if(item.parent == item) return item;
        return item.parent = find(item.parent); //With Path compression
    }

    @Override
    public void union(Item<T> first, Item<T> second) {
        Item<T> rootFirst = find(first);
        Item<T> rootSecond = find(second);

        if(rootFirst == rootSecond) return; //Already in the same set.

        if(rootFirst.size > rootSecond.size) {
            rootSecond.parent = rootFirst;
            rootFirst.size += rootSecond.size;
        } else {
            rootFirst.parent = rootSecond;
            rootSecond.size += rootFirst.size;
        }
    }

    @Override
    public void makeSet(T set) {
        new Item<>(set).parent = new Item<>(set);
        numSets++;
    }

    @Override
    public int getNumSets() {
        return numSets;
    }
}
