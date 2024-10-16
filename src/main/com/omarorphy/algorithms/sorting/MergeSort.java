//package com.omarorphy.algorithms.sorting;
//
//import java.util.Comparator;
//
//public class  MergeSort <V> {
//
//    private Comparator<V> comparator = null;
//
//    /**
//     * This constructor assumes that the V will be an instance of Comparaable
//     * and does not need aa Comparator.
//     */
//    public MergeSort() {
//        this(null);
//    }
//
//    /**
//     * This constructor provides a comparator to compare the values of type V
//     */
//    public MergeSort(Comparator<V> comparator) {
//        this.comparator = comparator;
//    }
////    public static void main(String[] args) {
////        int[] array = Util.createShuffledArray(15, 0, 25);
////        Util.println("Main Array before sort: %s", Arrays.toString(array));
////        sort(array);
////        Util.println("Main Array after sort: %s", Arrays.toString(array));
////    }
//
//    /**
//     * This method sorts an array of type V
//     * @param array is the array that will be sorted.
//     */
//    public void sort(V[] array) {
//        Object[] auxArray = new Object[array.length];
//        mergeSort(array, auxArray, 0, array.length - 1);
//    }
//
//    /**
//     * This method uses MergeSort algorithm to sort the array given as first argument.
//     * @param array This is the main array to be sorted.
//     * @param auxArray This is a copy of the main array in order to help sort the main array.
//     * @param start This is the start index where merge sort should be performed.
//     * @param end This is the end index where merge sort should be performed.
//     */
//    private void mergeSort(V[] array, Object[] auxArray, int start, int end) {
////        Util.println("Start: %s end: %s", start, end);
//        if (end - start < 1) {
//            return;
//        }
//        int mid = (start + end)/2;
//
////        Util.println("Did not return: Start: %s end: %s mid: %s array length: %s", start, end, mid, array.length);
//
//        mergeSort(array, auxArray, start, mid);
//        mergeSort(array, auxArray, mid + 1, end);
//        merge(array, auxArray, start, mid, end);
//    }
//
//    /**
//     * This method merges two sorted arrays. In this case it merges two parts of the
//     * auxiliary (copy) array that are seperated using the provided indices. First
//     * part of the array starts at start and ends at mid (Inclusive) and the second
//     * part of the array starts at mid + 1 and ends at end (Inclusive).
//     * @param mainArray Is the main Array where the result of the merge is stored.
//     * @param auxArray Is a used to keep a copy of the main array in order to compare
//     * and store in main array
//     * @param start Is the start of the first part of the array.
//     * @param mid is the end of the first part and beginning of the second part of the array.
//     * @param end is the end of the second part of the array.
//     */
//    private void merge(V[] mainArray, V[] auxArray, int start, int mid, int end) {
//
////        int[] arrayA = Arrays.copyOfRange(mainArray, auxArray, start, mid + 1);
////        int[] arrayB = Arrays.copyOfRange(mainArray, mid + 1, end + 1);
//        System.arraycopy(mainArray, start, auxArray, start, end + 1 - start);
////        Util.println("Merging A: %s", Arrays.toString(Arrays.copyOfRange(mainArray, start, mid + 1)));
////        Util.println("Merging B: %s", Arrays.toString(Arrays.copyOfRange(mainArray, mid + 1, end + 1)));
//        int i = start;
//        int j = mid + 1;
//
//        for(int k = start; k < end + 1; k++) {
//            while(i < mid + 1 && j < end + 1) {
//
////                Util.println("Comparing A: %s and B: %s", auxArray[i], auxArray[j]);
//                if(compare(auxArray[i], auxArray[j]) < 0) {
//                    mainArray[k++] = auxArray[i++];
//                } else {
//                    mainArray[k++] = auxArray[j++];
//                }
//            }
//
//            while(i < mid + 1) {
//                mainArray[k++] = auxArray[i++];
//            }
//
//            while(j < end + 1) {
//                mainArray[k++] = auxArray[j++];
//            }
//        }
//
////        Util.println("After merge main: %s", Arrays.toString(Arrays.copyOfRange(mainArray, start, end + 1)));
//    }
//
//    /**
//     * This method is a helper method in order to compare values. It checks whether
//     * there is a comparator presents, otherwise the values should extend Comparable.
//     * @param first This is the first value in comparison.
//     * @param second This is the second value in comparison.
//     * @return returns an int indicating whether first or second value is smaller. Return value
//     * can be negative, zero, or positive int indicating first value is smaller, equals or greater
//     * than second respectively.
//     */
//    private int compare(V first, V second) {
//        if(comparator != null) {
//            return comparator.compare(first, second);
//        } else {
//            if(V instanceof Comparable<V>) {
//                throw new IllegalArgumentException("Generic Type needs to extend Comparable or pass a Comparator " +
//                        "to the Constructor.");
//            }
//            return ((Comparable<V>) first).compareTo(second);
//        }
//    }
//}
