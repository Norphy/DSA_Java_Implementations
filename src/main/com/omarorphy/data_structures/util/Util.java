package com.omarorphy.data_structures.util;

import com.omarorphy.data_structures.BinarySearchTree.BSTNode;
import com.omarorphy.data_structures.BinarySearchTree.BinarySearchTree;

import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class Util {


    public static void print(String main, Object... reps) {
        String output = String.format(main, reps);
        System.out.print(output);
    }

    public static void println(String main, Object... reps) {
        String output = String.format(main, reps);
        System.out.println(output);
    }

    public static int[] createShuffledArray(int size, int lowerBound, int upperBound) {
        Set<Integer> set = new HashSet<>();
        RandomGenerator rand = new Random();
        rand.ints(lowerBound, upperBound).boxed().takeWhile(n -> set.size() < size).forEach(set::add);
        List<Integer> list = new ArrayList<>(set);
        Collections.shuffle(list);
        int[] array = new int[size];
        Iterator<Integer> listIt = list.iterator();
        for(int i = 0; i < size; i++) {
            array[i] = listIt.next();
        }
        return array;
    }

    public static Set<Integer> testing() {
        RandomGenerator rand = new Random();
        return rand.ints(0, 100).boxed().collect(Collectors.toSet());
    }

    /**
     * This Util method creates a randomly filled int array of
     * the desired size. Lower bound of random values is 0
     * and Upper bound for random values is 1000. It will
     * not contain duplicates.
     * @param size is the size of the desired array
     * @return randomly filled array
     */
    public static int[] createShuffledArray(int size) {
        return createShuffledArray(size, 0, 1000);
    }


    public static <T> void printNode(BSTNode<T> root) {
        int maxLevel = Util.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T> void printNodeInternal(List<BSTNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || Util.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        Util.printWhitespaces(firstSpaces);

        List<BSTNode<T>> newNodes = new ArrayList<>();
        for (BSTNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            Util.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Util.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    Util.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    Util.printWhitespaces(1);

                Util.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    Util.printWhitespaces(1);

                Util.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T> int maxLevel(BSTNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(Util.maxLevel(node.left), Util.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
