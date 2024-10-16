package com.omarorphy.data_structures.interfaces;

public interface PriorityQueue<V> {

    /**
     * This method extracts the highest element in the list (Or lowest if Minimum Priority Queue is implemented)
     * and removes it from the list.
     * @return the maximum element of the list (Minimum if Minimum Priority Queue was used)
     */
    public V extractValue();

    /**
     * Get the value of the highest element without removing it from the list (Or lowest if Minimum Priority Queue is implemented)
     * @return return the value of the maximum element (Minimum if Minimum Priority Queue was used)
     */
    public V peek();

    /**
     * Add a new element to the list
     * @param newItem is the element being added to the list
     */
    public void add(V newItem);

    /**
     * Returns whether the list is empty or not
     */
    public boolean isEmpty();
}
