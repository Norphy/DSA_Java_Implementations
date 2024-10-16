package com.omarorphy.data_structures;

import com.omarorphy.data_structures.interfaces.LinkedList;

import java.util.Arrays;
import java.util.Objects;

public class DoublyLinkedList<V> implements LinkedList<V> {

    private Node<V> head;
    private Node<V> tail;

    private static Node<String> headTest;
    private static Node<String> tailTest;
    private int size = 0;


    public static void main(String args[]) {

        Node<String> current = null;
        for(int i = 0; i < 10; i++) {
            Node<String> newNode = new Node<>("Hi " + i);
            if(headTest == null) {
                current = headTest = newNode;
            } else {
                Node<String> prev = current;
                current.next = newNode;
                current = current.next;
                current.prev = prev;
            }
        }

        tailTest = current;

        printArray(headTest);

        tailTest = tailTest.prev;
        tailTest.next = null;

        printArray(headTest);

        reverseTest();

        printArray(headTest);

    }

    public static void reverseTest() {
        System.out.println("Reverse");
        Node<String> current = headTest;
        while(current != null) {
            Node<String> next = current.next;
            current.next = current.prev;
            current.prev = next;
            current = current.prev;
        }
        Node<String> tempHead = headTest;
        headTest = tailTest;
        tailTest = tempHead;
    }

    public static void printArray(Node<String> headNode) {
        System.out.println("Head: " + headNode.data);
        Node<String> current = headNode;
        System.out.print("Sequence: ");
        while(current != null) {

            System.out.print(current.data + ", ");
            current = current.next;
        }

        System.out.println();
        System.out.println("Tail: " + tailTest.data);
    }

    /**
     * This method checks whether the chain is empty or not
     * @return boolean whether chain is empty or not
     */
    @Override
    public boolean isEmpty() { return size == 0;}

    /**
     * This method returns the size of the chain
     * @return number of the elements in the chain
     */
    @Override
    public int getSize() { return size; }

    /**
     * This method returns the value at a specific index
     * @param index is the index of the element that meant to be returned
     * @return the element at a specific index
     */
    @Override
    public V getValueAt(int index) {
        if(isEmpty()) throw new IndexOutOfBoundsException();
        Objects.checkIndex(0, size);

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
        if(isEmpty()) {
            head = new Node<>(data);
            tail = head;
        } else {
            Node<V> newNode = new Node<>(data);
            Node<V> first = head;
            first.prev = newNode;
            head = newNode;
            head.next = first;
        }
        size++;
    }

    /**
     * This method removes and returns the value at the front of the chain.
     * @return the value at the front of the chain
     */
    @Override
    public V popFront() {
        if(isEmpty()) return null;
        V firstValue = head.data;
        if(head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return firstValue;
    }

    /**
     * This method inserts a new element at the end of the chain
     * @param data is the value to be inserted at the end of the chain
     */
    @Override
    public void pushBack(V data) {
        Node<V> newNode = new Node<>(data);
        if(isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * This method removes and return the value at the end of the chain
     * @return the value at the end of the chain
     */
    @Override
    public V popBack() {
        if(isEmpty()) return null;
        V lastValue = tail.data;
        if(head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return lastValue;
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
            return tail.data;
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
        if(index == size) pushBack(data);
        else if(index == 0) pushFront(data);
        else {
            Node<V> newNode = new Node<>(data);
            Node<V> before = head;
            int currInd = 0;
            while( currInd < (index - 1)) {
                before = before.next;
                currInd++;
            }
            Node<V> next = before.next;
            before.next = newNode;
            next.prev = newNode;
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
        if(index == size - 1) popBack();
        else{
            int currInd = 0;
            Node<V> current = head;
            while(currInd < index - 1) {
                current = current.next;
                currInd++;
            }
            Node<V> before = current.prev;
            Node<V> next = current.next;
            before.next = next;
            next.prev = before;
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
        Node<V> end = tail;
        for(int counter = 0; counter < n; counter++) end = end.prev;
        return end.data;
    }

    /**
     * This method will reverse the list if it is not empty
     */
    @Override
    public void reverse() {
        if(!isEmpty()) {
            Node<V> current = head;
            while(current != null) {
                Node<V> next = current.next;
                current.next = current.prev;
                current.prev = next;
                current = current.prev;
            }
            Node<V> tempHead = head;
            head = tail;
            tail = tempHead;
        }
    }

    /**
     * This method will delete the element with a specific value if found (First occurance if value is repeated multiple times.
     * If list is empty, this method will have no effect;
     * @param value is the value of the element which we wish to delete
     */
    @Override
    public void removeValue(V value) {
        if(isEmpty()) return;
        Node<V> current = head;
        int currIndex = 0;
        while(current != null && current.data != value) {
            current = current.next;
            currIndex++;
        }
        if(current != null) erase(currIndex);
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
            if(current != null) sB.append(",");
        }
        sB.append("]");
        return sB.toString();
    }

    /**
     * This class is for the Node that is connected in the doubly linked chain
     * @param <V> is the class of the value to be stored in the node
     */
    private static class Node<V> {
        private V data;
        private Node<V> next;
        private Node<V> prev;

        private Node() {}

        private Node(V data) {
            this.data = data;
        }

        private Node(V data, Node next, Node prev) {
            this.data = data;
            this.next = next;
        }
    }
}
