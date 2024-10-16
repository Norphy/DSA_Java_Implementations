package com.omarorphy.data_structures.interfaces;

public interface LinkedList<V> {

    public boolean isEmpty();

    /**
     * This method returns the size of the chain
     * @return number of the elements in the chain
     */
    public int getSize();

    /**
     * This method returns the value at a specific index
     * @param index is the index of the element that meant to be returned
     * @return the element at a specific index
     */
    public V getValueAt(int index);

    /**
     * This method pushes an element at the front of the chain
     * @param data is the value that will be pushed at the front of the chain
     */
    public void pushFront(V data);

    /**
     * This method removes and returns the value at the front of the chain.
     * @return the value at the front of the chain
     */
    public V popFront();

    /**
     * This method inserts a new element at the end of the chain
     * @param data is the value to be inserted at the end of the chain
     */
    public void pushBack(V data);

    /**
     * This method removes and return the value at the end of the chain
     * @return the value at the end of the chain
     */
    public V popBack();

    /**
     * This method returns the value at the front of the chain without removing it
     * @return the value at the front of the chain
     */
    public V front();

    /**
     * This method returns the value at the end of the chain without removing it
     * @return the value at the end of the chain
     */
    public V back();

    /**
     * This method inserts a value at a specific index in the chain, if in the middle all the elements will be shifted to the right
     * @param index the index at which the new node is to be inserted
     * @param data is the value of the node to be inserted
     */
    public void insert(int index, V data);

    /**
     * Delete a node at a certain index. If list is empty does nothing. If index is 0 will simply popupFront() if index
     * points to last element will simply popBack()
     * @param index is the index of the element we wish to delete
     */
    public void erase(int index);

    /**
     * This method gets the value at the nth position from the end. If n is 0, will simply return the last element in the
     * list. If n is same as the size will get the value at the front of the list. If list is empty, will return null
     * @param n is the position from the end of the list at which we would like to return the value
     * @return the value of the element at the nth position from the end of the list
     */
    public V valueNFromEnd(int n);

    /**
     * This method will reverse the list if it is not empty
     */
    public void reverse();

    /**
     * This method will delete the element with a specific value if found (First occurance if value is repeated multiple times.
     * If list is empty, this method will have no effect;
     * @param value is the value of the element which we wish to delete
     */
    public void removeValue(V value);

    /**
     * This method returns a string form of the contents of the list
     * @return the string form of the list
     */
    public String toString();
}
