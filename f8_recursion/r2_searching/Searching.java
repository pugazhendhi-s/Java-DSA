package com.f8_recursion.r2_searching;

public class Searching {
    public static void main(String[] args) {

    }
    static int insertion(int[] arr, int size, int key, int capacity){
        if (size >= capacity)
            return size;
//        int i;
//        for (i = size-1; (i >= 0 && arr[i] > key); i--)
//            arr[i+1] = arr[i];
//        arr[i+1] = key;
        int j;
        for (j = 0; (j < size && arr[j] < key); j++)
            arr[size-j+1] = arr[size-j];
        arr[j] = key;
        return size + 1;
    }
    static int deletion(int[] arr, int size, int key){
        int position = BinarySearch.binarySearch(arr, 0, size, key);
        if (position == -1) {
            System.out.println("OOPS!..Element not found...");
            return size;
        }
        int i;
        for (i = position; i < size-1; i++) {
            arr[i] = arr[i+1];
        }
        return size-1;
    }
}
