package com.omarorphy.data_structures.HashMap;

import com.omarorphy.data_structures.interfaces.HashMapInterface;

import java.util.Arrays;
import java.util.Map;

public class HashMap <K, V> implements HashMapInterface<K, V> {

    private final int INITIAL_CAPACITY = 8;

    private Entry<K, V>[] bufferArray;
    private int size = 0;
    private int capacity = INITIAL_CAPACITY;
    private final int prime;
    private final int threshold;
    private final float LOAD_FACTOR = 0.75f;

    public HashMap() {
        bufferArray = new Entry[INITIAL_CAPACITY];
        threshold = (int) (capacity*LOAD_FACTOR);
        prime = 3;
    }

    /**
     * This method adds a new element or updates an exists element in the table
     * @param key is the key associated with the element we wish to insert in our table
     * @param value is the value associated with the element we wish to insert in our table
     */
    @Override
    public void add(K key, V value) {
        Entry<K, V> newAddition = new Entry<>(key, value);
        if(containsKey(key)) {
            bufferArray[getIndex(key)] = newAddition;
        } else {
            bufferArray[findEmptyIndex(key)] = newAddition;
            size++;
            checkTableSize();
        }
    }

    /**
     * This method returns a boolean value whether a key is currently in the HashTable
     * @param key is the key we wish to ask for
     * @return boolean whether key exists in HashTable
     */
    @Override
    public boolean containsKey(K key) {
        return (getIndex(key) != -1);
    }

    /**
     * This method returns the value associated with the given key
     * @param key is the key we wish to return a value for
     * @return value associated with the given key
     */
    @Override
    public V get(K key) {
        if(containsKey(key)) return bufferArray[getIndex(key)].getValue();
        else return null;
    }

    /**
     * This method removes a key value pair from the hash table using the key if it exists, if doesn't exist
     * in table, this method does not do anything
     * @param key is the key of the key value pair
     */
    @SuppressWarnings("unchecked")
    @Override
    public void remove(K key) {
        if(containsKey(key)) {
            bufferArray[getIndex(key)] = SentinelDeletedEntryObject.getSentinelObject();
        }
    }

    /**
     * This method creates a hash value for a key value using the table size
     * @param key is the key we wish to create a hash value for
     * @param tableSize is the value of our current hash table
     * @return return the hash value that is used to store the key in the hash table
     */
    private int hash(K key, int tableSize) {
        int h;
        int newHash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return (Math.abs(newHash) % tableSize);
    }

    /**
     * This method is a second hash function to be used in order of collisions
     * @param value is the value of the initial hash before the collision
     * @return return the hash value that is used to store the key in the hash table
     */
    private int hashTwo(int value) {
        return prime - (value%prime);
    }

    /**
     * This method returns the index of the key if it exists in the table, if it does not exist it will return -1.
     * <i><b>Collisions and Collision Resolution:</b></i>
     * Collision is a term used when a hash of two different keys are the same and end up wanting to occupy the same
     * index in the Hash table. There are two main ways to resolve this issue: Open Addressing and Chaining. Chaining
     * involves using LinkedList in the array slot and Open Addressing involves finding the next open slot.
     * In Open Addressing in order to find the next open slots there are multiple ways to find the next slot including
     * Linear Probing, Quadratic Probing and Double Hashing. Please google them if you wish to find out more information
     * about them. In this implementation we will be using Open Addressing method with Double Hashing technique and
     * that means that if we have a collision we will call another hashing method in order to find the next open slot.
     * @param key is the key associated to the key-value we wish to find the index for in our hash table.
     * @return index of the key-value element associated with the given key
     */
    private int getIndex(K key) {
        int index = hash(key, capacity);
        int offset = hashTwo(index);
        int originalHash = -1;

        while((bufferArray[index] == SentinelDeletedEntryObject.getSentinelObject() || !bufferArray[index].key.equals(key)) && index != originalHash) {
            if(originalHash == -1) originalHash = index;
            index = index + offset;
        }
        if(bufferArray[index] == null) return -1;
        else return index;
    }

    /**
     * This is a helper method in case the key is not in our table to find the next available empty slot in order to
     * insert our key
     * @param key is the key we wish to insert in our table
     * @return the index of the empty slot to insert our key
     */
    private int findEmptyIndex(K key) {
        int index = hash(key, capacity);
        int offset = hashTwo(index);
        while((bufferArray[index] == SentinelDeletedEntryObject.getSentinelObject() ||
                bufferArray[index] != null)) index = index + offset;
        return index;
    }

    /**
     * This is a helper method to check if the Table needs resizing due to growing in number of elements being added
     */
    private void checkTableSize() {
        if(size >= threshold) {
            capacity = capacity*2;
            Entry<K, V>[] tempArray = Arrays.copyOf(bufferArray, bufferArray.length);
            bufferArray = new Entry[capacity];
            for (Entry<K, V> entry : tempArray) {
                if (entry != null && !entry.equals(SentinelDeletedEntryObject.getSentinelObject())) {
                    int ind = findEmptyIndex(entry.getKey());
                    Entry<K, V> kv = new Entry<>(entry.getKey(), entry.getValue());
                    bufferArray[ind] = kv;
                }
            }
        }
    }

    private int getAppropriatePrime(int n) {
        boolean[] notPrime = new boolean[n];
        for(int i = 2; i < n; i = i + 1) {
            int val = i + i;

            if(val >= n) continue;
            if(!notPrime[val]) {
                while(val < n) {
                    System.out.println("Is not prime: " + val);
                    notPrime[val] = true;
                    val += i;
                }
            }

        }
        for(int i = n - 1; i > 1; i--) {
            if(!notPrime[i]) {
                return i;
            }
        }
        //Assertions.fail("Failed to find prime");
        return 3;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private final V value;

        public Entry (K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        @Deprecated
        public V setValue(V value) {
            return null;
        }
    }

    /**
     * This is an object that represents where an entry was deleted in order to know to continue to that it is not
     * the end of the search in case collisions caused entries to be saved past this point
     */
    private static class SentinelDeletedEntryObject<K, V> extends Entry<K, V> {

        private static SentinelDeletedEntryObject entry = null;

        private SentinelDeletedEntryObject() {
            super(null, null);
        }

        public static SentinelDeletedEntryObject getSentinelObject() {
            if(entry == null) {
                entry = new SentinelDeletedEntryObject();
            }
            return entry;
        }
    }
}
