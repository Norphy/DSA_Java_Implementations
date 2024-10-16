package com.omarorphy.data_structures;

import com.omarorphy.data_structures.interfaces.LinkedList;

import java.util.Objects;

public class SinglyLinkedList<V> implements LinkedList<V> {

    private Node<V> head = null;
    private int size = 0;

    /**
     * This method checks whether the chain is empty or not
     * @retun boolean whether chain is empty or not
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method returns the size of the chain
     * @return number of the elements in the chain
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * This method returns the value at a specific index
     * @param index is the index of the element that meant to be returned
     * @return the element at a specific index
     */
    @Override
    public V getValueAt(int index) {
        if(isEmpty()) return null;
        Objects.checkIndex(index, size);

        Node<V> next = head;
        int counter = 0;
        while(counter != index) {
            next = next.next;
            counter++;
        }
        return next.data;
    }

    /**
     * This method pushes an element at the front of the chain
     * @param data is the value that will be pushed at the front of the chain
     */
    @Override
    public void pushFront(V data) {
        Node<V> newNode = new Node<>(data);
        if(!isEmpty()) newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * This method removes and returns the value at the front of the chain.
     * @return the value at the front of the chain
     */
    @Override
    public V popFront() {
        if(!isEmpty()) {
            V value = head.data;
            head = head.next;
            size--;
            return value;
        } else {
            return null;
        }
    }

    /**
     * This method inserts a new element at the end of the chain
     * @param data is the value to be inserted at the end of the chain
     */
    @Override
    public void pushBack(V data) {
        if(isEmpty()) {
            insert(0, data);
        } else {
            Node<V> nodeAtBack = nodeAtBack();
            nodeAtBack.next = new Node<>(data, null);
            size++;
        }
    }

    /**
     * This is a helper method that returns the node at the end of the chain
     * @return the node at the end of the chain
     */
    private Node<V> nodeAtBack() {

        if(isEmpty()) {
            return null;
        } else {
            Node<V> next = head;
            while(next.next != null) {
                next = next.next;
            }
            return next;
        }
    }

    /**
     * This method removes and return the value at the end of the chain
     * @return the value at the end of the chain
     */
    @Override
    public V popBack() {
        if(isEmpty()) {
            return null;
        } else if(size == 1) {
            V value = head.data;
            head = null;
            size--;
            return value;
        } else {
            Node<V> next = head;
            while(next.next != null && next.next.next != null) {
                next = next.next;
            }
            V value = next.next.data;
            next.next = null;
            size--;
            return value;
        }
    }

    /**
     * This method returns the value at the front of the chain without removing it
     * @return the value at the front of the chain
     */
    @Override
    public V front() {
        if(isEmpty()) {
            return null;
        } else {
            return head.data;
        }
    }

    /**
     * This method returns the value at the end of the chain without removing it
     * @return the value at the end of the chain
     */
    @Override
    public V back() {
        if(isEmpty()) {
            return null;
        } else {
            return nodeAtBack().data;
        }
    }

    /**
     * This method inserts a value at a specific index in the chain, if in the middle all the elements will be shifted to the right
     * @param index the index at which the new node is to be inserted
     * @param data is the value of the node to be inserted
     */
    @Override
    public void insert(int index, V data) {
        Objects.checkIndex(index, size + 1);

        Node<V> newNode = new Node<>(data, null);
        if(isEmpty()) {
            head = newNode;
            size++;
        } else if(index == size) pushBack(data);
        else if(index == 0) pushFront(data);
        else {
            Node<V> curr = head;
            Node<V> prev = head;
            for(int counter = 0; counter < index; counter++) {
                prev = curr;
                curr = curr.next;
            }
            newNode.next = curr;
            prev.next = newNode;
            size++;
        }
    }

    /**
     * Delete a node at a certain index. If list is empty does nothing. If index is 0 will simply popupFront() if index
     * points to last element will simply popBack()
     * @param index is the index of the element we wish to delete
     */
    @Override
    public void erase(int index) {
        if(isEmpty()) return;
        Objects.checkIndex(index, size);
        if(index == 0) popFront();
        else if(index == size - 1) popBack();
        else {
            Node<V> current = head;
            for (int counter = 0; counter < index - 1; counter++) current = current.next;
            current.next = current.next.next;
            size--;
        }
    }

    /**
     * This method gets the value at the nth position from the end. If n is 0, will simply return the last element in the
     * list. If n is same as the size will get the value at the front of the list. If list is empty, will return null
     * @param n is the position from the end of the list at which we would like to return the value
     * @return the value of the element at the nth position from the end of the list
     */
    @Override
    public V valueNFromEnd(int n) {
        if(isEmpty()) return null;
        Objects.checkIndex(n, size);
        if(n == 0) return back();
        if(n == size - 1) return front();

        int nTh = (size - 1 - n);
        Node<V> current = head;
        for(int counter = 0; counter < nTh; counter++) {
            current = current.next;
        }
        return current.data;

    }

    /**
     * This method will reverse the list if it is not empty
     */
    @Override
    public void reverse() {
        if(!isEmpty()) {
            Node<V> current = head;
            Node<V> newChain = new Node<V>(current.data, null);
            while(current.next != null) {
                current = current.next;
                newChain = new Node<V>(current.data, newChain);
            }
            head = newChain;
        }
    }

    /**
     * This method will delete the element with a specific value if found (First occurrence if value is repeated multiple times.
     * If list is empty, this method will have no effect;
     * @param value is the value of the element which we wish to delete
     */
    @Override
    public void removeValue(V value) {
        if(!isEmpty()) {
            Node<V> current = head;
            int currentIndex = 0;
            do {
                if(current.data == value) {
                    erase(currentIndex);
                    break;
                }
                currentIndex++;
                current = current.next;
            } while(current != null);
        }
    }

    /**
     * This method returns a string form of the contents of the list
     * @return the string form of the list
     */
    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        Node<V> current = head;
        sB.append("[");
        while(current != null) {

            sB.append(current.data);
            current = current.next;
            if(current != null) sB.append(", ");
        }
        sB.append("]");
        return sB.toString();
    }

    /**
     * This class is for the Node that is connected in the singly linked chain
     * @param <V> is the class of the value to be stored in the node
     */
    private static class Node<V> {
        private V data;
        private Node<V> next;

        private Node() {}

        private Node(V data) {
            this.data = data;
        }

        private Node(V data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}