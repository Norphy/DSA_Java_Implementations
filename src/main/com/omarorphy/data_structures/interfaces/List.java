package com.omarorphy.data_structures.interfaces;

public interface List<V> {

    /**
     * This method pushes a new value into our array.
     * @param newValue is the new value being added into the array.
     */
    public void pushValue(V newValue);

    /**
     * This method gets an element from the array at a specific index
     * @param i is the index at which to retrieve an element
     * @return the desired element
     */
    @SuppressWarnings("unchecked")
    public V get(int i);

    /**
     * This method sets a value to a specific index, overwriting its previous value. If the index is higher exactly one
     * higher than the size, it will just push the value at the end of the array.
     * @param i is the index at which to set the desired value
     * @param newValue is the new element to be set in the array
     */
    public void setValue(int i, V newValue);

    /**
     * This method returns the current size of the array (The number of elements stored in the array not the capacity)
     * @return size of array
     */
    public int size();

    /**
     * This method returns the capacity of the array (The maximum number of elements the array is able to hold before expanding)
     * @return the capacity of the array
     */
    public int capacity();

    /**
     * This method returns whether the array has any elements stored in it or not.
     * @return boolean whether the array is empty
     */
    public boolean isEmpty();

    /**
     * This method will insert a new value into the array without overwriting any of the current elements. If the
     * index is in the middle of the elements, it will slide all the elements to its right.
     * @param index is the index at which the new element will be inserted
     * @param newItem is the new element being inserted
     */
    public void insert(int index, V newItem);

    /**
     * This method adds the value at the beginning of the array, and shifts the entire array towards the right.
     * @param newItem is the new element to be added in the array
     */
    public void prepend(V newItem);

    /**
     * This method returns the last element in the array and removes it from the array
     * @return the last element in the array
     */
    public V pop();

    /**
     * This deletes any element within the array by index and shifts the rest of the elements accordingly
     * @param index is the index of the element to be deleted
     */
    public void delete(int index);

    /**
     * This method removes a value if it is within the array and will remove all instances of it, if it is not found
     * in the array, this method has no affect.
     * @param element is the element to be deleted within the array.
     * @return boolean whether the item was found and deleted or was not found.
     */
    public boolean remove(V element);

    /**
     * This method attempts to look for a value and if it is found, it will return the index and if it is not found,
     * it will return -1. If there are multiple occurances of the element in the array, it will return the first one.
     * @param element is the element to be found in the array
     * @return the index of the element
     */
    public int find(V element);
}
