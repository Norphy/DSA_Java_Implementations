package com.omarorphy.data_structures;

import com.omarorphy.data_structures.interfaces.List;
import com.omarorphy.data_structures.interfaces.PriorityQueue;

import java.util.Comparator;

public class Heap <V> implements PriorityQueue<V> {

    private final List<V> list;
    private int size = 0;
    private static final int DEFAULT_ARRAY_SIZE = 8;
    private Comparator<? super V> comparator = null;

    public Heap(int size) {
        this(size, null);
    }

    public Heap() {
        this(DEFAULT_ARRAY_SIZE, null);
    }

    public Heap(int size, Comparator<? super V> comparator) {
        list = new ArrayList<>(size);
        this.comparator = comparator;
    }

    /**
     * This method extracts the highest element in the list (Or lowest if Minimum Priority Queue is implemented)
     * and removes it from the list.
     * @return the maximum element of the list (Minimum if Minimum Priority Queue was used)
     */
    @Override
    public V extractValue() {
        V value = list.get(0);
        list.setValue(0, list.get(size - 1));
        size--;
        heapify(0);
        return value;
    }

    /**
     * Get the value of the highest element without removing it from the list (Or lowest if Minimum Priority Queue is implemented)
     * @return return the value of the maximum element (Minimum if Minimum Priority Queue was used)
     */
    @Override
    public V peek() {
        return list.get(0);
    }

    /**
     * Add a new element to the list
     * @param newItem is the element being added to the list
     */
    @Override
    public void add(V newItem) {
        list.pushValue(newItem);
        heapify(size);
        size++;
    }

    /**
     * Returns whether the list is empty or not
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void heapify(int index) {
        if(comparator != null) {
            if(!heapifyUpComparator(index)) heapifyDownWithComparator(index);
        } else {
            if(!heapifyUpComparable(index)) heapifyDownWithComparable(index);
        }
    }

    private void heapifyDownWithComparator(int index) {
        if(isLeaf(index)) return;
        int biggestChildInd = getLeftChild(index);

        int rightChildInd = getRightChild(index);
        //If biggestChild is less than rightChild, set it to rightChild
        if(rightChildInd < size &&
                comparator.compare(list.get(biggestChildInd), list.get(rightChildInd)) < 0)
            biggestChildInd = rightChildInd;

        //Current is already bigger than its biggest child
        if(comparator.compare(list.get(biggestChildInd), list.get(index)) < 0) return;

        //Swap values
        V biggest = list.get(biggestChildInd);
        list.setValue(biggestChildInd,  list.get(index));
        list.setValue(index, biggest);

        heapifyDownWithComparator(biggestChildInd);
    }

    @SuppressWarnings("unchecked")
    private void heapifyDownWithComparable(int index) {

        if(isLeaf(index)) return;
        int biggestChildInd = getLeftChild(index);
        int rightChildInd = getRightChild(index);

        //If it has a rightChild and biggestChild is smaller than rightChild, set it to rightChild to biggestChild
        if(rightChildInd < size &&
                (((Comparable<? super V>) list.get(biggestChildInd)).compareTo(list.get(rightChildInd)) < 0)) {

            biggestChildInd = rightChildInd;
        }

        //Current is already bigger than its biggest child
        if(((Comparable<? super V>) list.get(biggestChildInd)).compareTo(list.get(index)) < 0) {
            return;
        }

        //Swap values
        V biggest = list.get(biggestChildInd);
        list.setValue(biggestChildInd,  list.get(index));
        list.setValue(index, biggest);

        //Continue heapifying down recursively
        heapifyDownWithComparable(biggestChildInd);
    }

    private boolean heapifyUpComparator(int index) {
        if(index == 0) return false;
        int parentInd = getParent(index);
        if(comparator.compare(list.get(parentInd), list.get(index)) > 0) return false;

        //Swap values
        V parent = list.get(parentInd);
        list.setValue(parentInd,  list.get(index));
        list.setValue(index, parent);
        heapifyUpComparator(parentInd);
        return true;
    }

   @SuppressWarnings("unchecked")
    private boolean heapifyUpComparable(int index) {
        if(index == 0) return false;
        int parentInd = getParent(index);
        if(((Comparable<? super V>) list.get(parentInd)).compareTo(list.get(index)) > 0) return false;

        //Swap values
        V parent = list.get(parentInd);
        list.setValue(parentInd,  list.get(index));
        list.setValue(index, parent);
        heapifyUpComparable(parentInd);
        return true;
    }

    private int getLeftChild(int index) { return index*2 + 1; }

    private int getRightChild(int index) { return index*2 + 2; }

    private int getParent(int index) { return (index - 1) / 2; }

    private boolean isLeaf(int index) { return index >= (size / 2);}
}
