package com.omarorphy.data_structures;

import com.omarorphy.data_structures.interfaces.List;

import java.util.Objects;

/**
 * Dynamic Array Data Structure which uses an underlying fixed array and creates a new array
 * with a bigger or a smaller size if need be as the user needs
 * @author Omar Orphy
 */
public class ArrayList<V> implements List<V> {

    //Initial size of the underlying array, the capacity will grow when the user needs it.
    private final int INITIAL_CAPACITY = 2;
    //The underlying array
    private Object[] mainArray;
    //Is the size of the contents of the array not the length of the array
    private int size = 0;
    //Is the multiplier at which the array grows when it hits full capacity
    private final int ARRAY_SIZE_GROWTH_FACTOR = 2;



    /**
     * {@inheritDoc}
     */
    public ArrayList(int initialCapacity) {
        mainArray = new Object[initialCapacity];
    }

    /**
     * A constructor that uses the default capacity for our array INITIAL_CAPACITY
     */
    public ArrayList() {
        mainArray = new Object[INITIAL_CAPACITY];
    }

    /**
     * This method pushes a new value into our array.
     * @param newValue is the new value being added into the array.
     */
    @Override
    public void pushValue(V newValue) {
        ensureCapacity();
        mainArray[size] = newValue;
        size++;
    }


    /**
     * This method ensures that there is enough capacity to add a new element in the array, if it is full
     * it will be increase the size of the array.
     */
    private void ensureCapacity() {
        if(size == mainArray.length) {
            increaseArraySize();
        }
    }

    /**
     * This method increases the size of the array by the ARRAY_SIZE_GROWTH_FACTOR
     */
    private void increaseArraySize() {
        Object[] newArray = new Object[mainArray.length*ARRAY_SIZE_GROWTH_FACTOR];
        System.arraycopy(mainArray, 0, newArray, 0, mainArray.length);
        mainArray = newArray;
    }

    /**
     * This method gets an element from the array at a specific index
     * @param i is the index at which to retrieve an element
     * @return the desired element
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(int i) {
        Objects.checkIndex(i, size);
        return (V) mainArray[i];

    }

    /**
     * This method sets a value to a specific index, overwriting its previous value. If the index is higher exactly one
     * higher than the size, it will just push the value at the end of the array.
     * @param i is the index at which to set the desired value
     * @param newValue is the new element to be set in the array
     */
    @Override
    public void setValue(int i, V newValue) {
        Objects.checkIndex(i, size + 1);
        if(i == (size)) pushValue(newValue);
        else mainArray[i] = newValue;
    }

    /**
     * This method returns the current size of the array (The number of elements stored in the array not the capacity)
     * @return size of array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method returns the capacity of the array (The maximum number of elements the array is able to hold before expanding)
     * @return the capacity of the array
     */
    @Override
    public int capacity() {
        return mainArray.length;
    }

    /**
     * This method returns whether the array has any elements stored in it or not.
     * @return boolean whether the array is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method will insert a new value into the array without overwriting any of the current elements. If the
     * index is in the middle of the elements, it will slide all the elements to its right.
     * @param index is the index at which the new element will be inserted
     * @param newItem is the new element being inserted
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insert(int index, V newItem) {
        Objects.checkIndex(index, size + 1);
        if(index == (size)) {
            pushValue(newItem);
        } else {
            pushValue((V) mainArray[size - 1]);
            System.arraycopy(mainArray, index, mainArray, index + 1, size - 1 - index);
            mainArray[index] = newItem;
        }
    }

    /**
     * This method adds the value at the beginning of the array, and shifts the entire array towards the right.
     * @param newItem is the new element to be added in the array
     */
    @Override
    public void prepend(V newItem) {
        insert(0, newItem);
    }

    /**
     * This method returns the last element in the array and removes it from the array
     * @return the last element in the array
     */
    @SuppressWarnings("unchecked")
    @Override
    public V pop() {
        V lastElement = (V) mainArray[size - 1];
        mainArray[size - 1] = '\0';
        size--;
        return lastElement;
    }

    /**
     * This deletes any element within the array by index and shifts the rest of the elements accordingly
     * @param index is the index of the element to be deleted
     */
    @Override
    public void delete(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(mainArray, index + 1, mainArray, index, size - index - 1);
        mainArray[size - 1] = '\0';
        size--;
    }

    /**
     * This method removes a value if it is within the array and will remove all instances of it, if it is not found
     * in the array, this method has no affect.
     * @param element is the element to be deleted within the array.
     * @return boolean whether the item was found and deleted or was not found.
     */
    @Override
    public boolean remove(V element) {
        boolean found = false;
        for(int i = 0; i < size; i++) {
            if(mainArray[i] == element) {
                delete(i);
                i--;
                found = true;
            }
        }
        return found;
    }

    /**
     * This method attempts to look for a value and if it is found, it will return the index and if it is not found,
     * it will return -1. If there are multiple occurances of the element in the array, it will return the first one.
     * @param element is the element to be found in the array
     * @return the index of the element
     */
    @Override
    public int find(V element) {
        for(int i = 0; i < size; i++) {
            if(mainArray[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method converts the values of the array to a String
     * @return the string form of the array
     */
    @Override
    public String toString() {
        String arrayString = "[";
        for(int i = 0; i < size; i++) {
            arrayString = arrayString + mainArray[i];
            if(i != size - 1) {
                arrayString = arrayString + ", ";
            }
        }
        arrayString = arrayString + "]";
        return arrayString;
    }
}
