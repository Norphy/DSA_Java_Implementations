package com.omarorphy.data_structures.interfaces;

public interface HashMapInterface<K, V> {

    public void add(K key, V value);

    public boolean containsKey(K key);

    public V get(K key);

    public void remove(K key);

}
