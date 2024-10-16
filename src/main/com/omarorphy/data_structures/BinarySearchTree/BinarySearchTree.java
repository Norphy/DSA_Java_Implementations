package com.omarorphy.data_structures.BinarySearchTree;

import com.omarorphy.data_structures.interfaces.IBinarySearchTree;
import com.omarorphy.data_structures.util.Util;

import java.util.*;

/**
 * This is a Binary Search Tree Implementation. Binary Search Tree is a Data Structure
 * where each node has no more than two children and follows a rule where all the nodes
 * in the left child Subtree contains values less than (or equal to) the value in the current node and
 * all the nodes in the right child Subtree hold values greater than the value of the current node.
 * @param <V> is the type of the value a node holds. It should either implement Comparable or
 *           a comparator needs to be passed during instantiation. If neigher of these conditions are met,
 *           there is risk of ClassCastException being thrown.
 */
public class BinarySearchTree <V> implements IBinarySearchTree<V> {

    private BSTNode<V> root;
    private Comparator<? super V> comparator = null;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<V> comparator) {
        this.comparator = comparator;
    }

    /**
     * This method checks whether the tree is empty or not
     * @return boolean value of whether the tree is empty or not
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * This method inserts a new value into the Binary Search Tree.
     * @param value is the value to be inserted into the Binary Search Tree
     */
    @Override
    public void insertValue(V value) {
        if(isEmpty()) {
            this.root = new BSTNode<>(value, null, null);
        } else {
            BSTNode<V> curr = this.root;
            BSTNode<V> prev = null;
            while(curr != null) {
                if(compare(curr.value,(V) value) > 0) {
                    prev = curr;
                    curr = curr.left;
                } else {
                    prev = curr;
                    curr = curr.right;
                }
            }

            BSTNode<V> newNode = new BSTNode<>(value, null, null);
            //prev will not be null unless the this.root is null. Since we already checked
            //if this.root is null through isEmpty() call, prev will not be null.
            @SuppressWarnings("ConstantConditions")
            int comp = compare(prev.value, value);
            if(comp > 0) {
                prev.left = newNode;
            } else {
                prev.right = newNode;
            }
        }
    }

    /**
     * This method deletes a Node that holds a value passed as a parameter.
     * @param value is the value we which to delete in the tree/subtree
     */
    @Override
    public void deleteValue(V value) {
        deleteValue(this.root, value);
    }

    /**
     * This method deletes a Node that holds a value passed as a parameter.
     * @param curr is the root node of a tree or subtree where the intended value
     *             is to be deleted.
     * @param value is the value we which to delete in the tree/subtree
     * @return returns the root of the tree/subtree.
     */
    private BSTNode<V> deleteValue(BSTNode<V> curr, V value) {
        if(compare(curr.value, value) > 0) {
            curr.left = deleteValue(curr.left, value);
        } else if(compare(curr.value, value) < 0) {
            curr.right = deleteValue(curr.right, value);
        } else {
            //Case one: Node is Leaf
            if(curr.left == null && curr.right == null) {
                return null;
            }

            //Case two: Node has one child
            else if(curr.left == null) {
                return curr.right;
            } else if(curr.right == null) {
                return curr.left;
            }

            //Case three: Node has two children
            else {
                BSTNode<V> maxNode = findMaxNode(curr.left);
                //maxNode will only be null if curr.left is null, since we already made sure
                //that curr.left is not null in prev if check, NPE will not be thrown
                @SuppressWarnings("ConstantConditions")
                V maxNodeVal = maxNode.value;
                curr.value = maxNodeVal;
                curr.left = deleteValue(curr.left, maxNodeVal);
            }
        }
        return curr;
    }

    /**
     * This method deletes the entire tree by deleting the root reference.
     */
    @Override
    public void deleteTree() {
        this.root = null;
    }

    /**
     * This method checks whether a value is held by any of the nodes in the tree.
     * @param value is the value we wish to check whether in the tree or not
     * @return a boolean value whether the parameter value is found in tree
     */
    @Override
    public boolean isValueInTree(V value) {
        return findNode(value) != null;
    }

    /**
     * This method looks for and returns the node that holds the value passed as a parameter.
     * @param value is the value we wish to return the node that holds.
     * @return returns the node that holds the value passed as a parameter. If null, the value is
     * not found in the tree.
     */
    @Override
    public BSTNode<V> findNode(V value) {
        if(isEmpty()) {
            return null;
        }

        BSTNode<V> curr = this.root;
        while(curr != null && !curr.value.equals(value)) {
            if(compare(curr.value, value) > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return curr;
    }

    /**
     * This method prints the Binary Search Tree
     */
    @Override
    public void printTree() {
         Util.printNode(this.root);
    }

    /**
     * This method returns the height of the tree.
     * @return return int value that returns the height of the tree.
     */
    @Override
    public int getHeight() {
        if(isEmpty()) return 0;

        Queue<BSTNode<?>> q = new LinkedList<>();
        q.add(this.root);
        Queue<BSTNode<?>> currList = new LinkedList<>();
        int level = 0;
        while(!q.isEmpty()) {
            while(!q.isEmpty()) {
                BSTNode<?> curr = q.poll();
                if(curr.left != null) currList.add(curr.left);
                if(curr.right != null) currList.add(curr.right);
            }
            q.addAll(currList);
            currList.clear();
            level++;
        }

        return level;
    }


    /**
     * This method returns the minimum value in the entire tree. (Not the Node that holds the value)
     * @return the minimum value in the tree.
     */
    @Override
    public V findMinValue() {
        //If empty tree
        if(isEmpty()) return null;


        BSTNode<V> minNode = findMinNode(this.root);
        //minNode will only be null if the tree is empty.
        //Since we return null if tree is empty, minNode will not be null
        @SuppressWarnings("ConstantConditions")
        V val = minNode.value;
        return val;
    }

    /**
     * This method returns the maximum value in the entire tree. (Not the Node that holds the value)
     * @return return the maximum value in the tree.
     */
    @Override
    public V findMaxValue() {
        //If empty tree
        if(isEmpty()) return null;

        BSTNode<V> maxNode = findMaxNode(this.root);
        //maxNode will only be null if the tree is empty.
        //Since we return null if tree is empty, maxNode will not be null
        @SuppressWarnings("ConstantConditions")
        V val = maxNode.value;
        return val;
    }

    /**
     * This method checks whether this tree is a Binary Search Tree, meaning it follows the rule that
     * at any given node in the tree, all nodes in the left child subtree hold values less than or equal to
     * the value in the current node and all the nodes in the right child subtree hold values greater than
     * the value held in the current node.
     * @return boolean value whether tree is Binary Search Tree or not.
     */
    @Override
    public boolean isBinarySearchTree() {
        return dfs(this.root, null, null);
    }

    private boolean dfs(BSTNode<V> curr, V minVal, V maxVal) {
        if(curr == null) return true;

        V currVal = curr.value;
        if(!dfs(curr.left, minVal, currVal)) return false;
        if(minVal != null && compare(currVal, minVal) < 0) return false;
        if(maxVal != null && compare(currVal, maxVal) > 0) return false;
        return dfs(curr.right, currVal, maxVal);
    }

    /**
     * This method returns Successor of the value passed as a parameter, meaning the next in the sequence
     * of the Binary Search Tree.
     * @param value is the value that we wish to get the Successor for.
     * @return returns the Node that holds the successor value.
     */
    @Override
    public BSTNode<V> getSuccessor(V value) {
        if(isEmpty()) return null;

        BSTNode<V> prev = null;
        BSTNode<V> curr = this.root;
        while(curr != null && !curr.value.equals(value)) {
            if(compare(curr.value, value) > 0) {
                prev = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        //Value doesn't exist in tree
        if(curr == null) {
            return null;
        }

        //If it has right subtree, get min value in right subtree
        if(curr.right != null) {
            return findMinNode(curr.right);
        }

        //This Node is the deepest node where the value we are looking for
        //is in its left side. If this is null, the value we are looking for
        //is the highest in the Tree and therefore has no Successor.
        return prev;
    }

    /**
     * This method returns Predecessor of the value passed as a parameter, meaning the previous in the sequence
     * of the Binary Search Tree.
     * @param value is the value that we wish to get the Predecessor for.
     * @return returns the Node that holds the predecessor value.
     */
    @Override
    public BSTNode<V> getPredecessor(V value) {
        if(isEmpty()) return null;

        BSTNode<V> prev = null;
        BSTNode<V> curr = this.root;

        while(curr != null && !curr.value.equals(value)) {
            if(compare(curr.value, value) > 0) {
                curr = curr.left;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }

        //Value doesn't exist in Tree
        if(curr == null) {
            return null;
        }

        if(curr.left != null) {
            return findMaxNode(curr.left);
        }

        //This Node is the deepest node where the value we are looking for
        //is in its right side. If this is null, the value we are looking for
        //is the lowest in the Tree and therefore has no Predecessor.
        return prev;
    }

    /**
     * This is a helper method that returns the node that holds the maximum value of the
     * subtree that is passed as a parameter
     * @param root is the root node of the tree/subtree
     * @return returns the node that holds the maximum value
     */
    private BSTNode<V> findMaxNode(BSTNode<V> root) {
        BSTNode<V> curr = root;
        if(curr == null) {
            return null;
        }
        while(curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    /**
     * This is a helper method that returns the node that holds the minimum value of the
     * subtree that is passed as a parameter
     * @param root is the root node of the tree/subtree
     * @return returns the node that holds the minimum value
     */
    private BSTNode<V> findMinNode(BSTNode<V> root) {
        BSTNode<V> curr = root;
        if(curr == null) {
            return null;
        }

        while(curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    //TODO remove
    public static void main(String[] args) {

        int[] array = Util.createShuffledArray(8, 0, 99);
        Util.println(Arrays.toString(array));
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Arrays.stream(array).forEach(bst::insertValue);
        bst.printTree();
        Util.println("Is binary: %s", bst.isBinarySearchTree());
        BSTNode<?> pred = bst.getPredecessor(array[3]);
        Util.println("Pred of: %s is: %s", array[3], (pred == null)? null : pred.value);
    }

    /**
     * This method is a helper method in order to compare values. It checks whether
     * there is a comparator presents, otherwise the values should extend Comparable.
     * @param first This is the first value in comparison.
     * @param second This is the second value in comparison.
     * @return returns an int indicating whether first or second value is smaller. Return value
     * can be negative, zero, or positive int indicating first value is smaller, equals or greater
     * than second respectively.
     */
    private int compare(V first, V second) {
        if(comparator != null) {
            return comparator.compare(first, second);
        } else {
            @SuppressWarnings("unchecked")
            //Throws ClassCastException if Value is not Comparable
            int result = ((Comparable<V>) first).compareTo(second);
            return result;
        }
    }
}
