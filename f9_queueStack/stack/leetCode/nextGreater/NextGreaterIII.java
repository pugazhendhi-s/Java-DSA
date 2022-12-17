package com.f9_queueStack.stack.leetCode.nextGreater;

public class NextGreaterIII {

    public static void main(String[] args) {

    }

    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1])
            i--;

        if (i < 0) return -1;

        int j = arr.length - 1;
        while (arr[j] <= arr[i])
            j--;

        swap(arr, i, j);
        reverse(arr, i + 1, arr.length - 1);

        try {
            return Integer.parseInt(String.valueOf(arr));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static void swap(char[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    static void reverse(char[] arr, int i, int j) {
        int l = i, h = j;
        while (l < h)
            swap(arr, l++, h--);
    }
}
