package com.f1_arrays.rotation;

public class Problems {
    public static void main(String[] args) {

    }
    static void maxHammingDistanceByRotation() {
        /*int[] a = {1,4,1};
        Arrays.sort(a);
        int []temp = new int[2*a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
            temp[a.length+i] = a[a.length-1-i];
        }
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i]+" ");
        }
        int count = 0;
        System.out.println("\nLength of duplicate array : "+ temp.length);
        for (int i = 0; i < temp.length/2; i++) {
            if(temp[i] != temp[temp.length/2+i])
                count++;
        }
        System.out.print("Max hamming distance :"+ count);
        */
        /* Approch 2 by rotation using extra array
         int[] a = {2,4,6,8};
         int n = a.length;
         int[] b = new int[2*n];
        for (int i = 0; i < n; i++) {
            b[i] = a[i];
            b[n+i] = a[i];
        }
         int maxhamm = -1;
        for (int i = 1; i < n; i++) { // (n-1) 3 rotation is enough.
            int curhamm = 0;
            // {2,4,6,8} {2,4,6,8,2,4,6,8}
            for (int j=i, k=0; j < (n+i); j++,k++) {  // j for b array and k for a array
                if(b[j] != a[k])
                    curhamm++;
            }
            if(curhamm > maxhamm)
                maxhamm = curhamm; // or maxhamm = Math.max(maxhamm, curhamm);
        }
        System.out.println("Maximum Hamming distance : "+maxhamm);
        System.out.println("Time Complexity : O(n^2) and Space Complexity O(n)");*/
        int[] a = {2, 4, 6, 8};
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int hamm = 0;
            for (int j = 0; j < n; j++) {
                if (a[j] != a[(j + i) % n])
                    hamm++;
            }
            if (hamm == n) {
                System.out.println("Maximum Hamming distance : " + hamm);
                System.out.println("Time Complexity : O(n^2) and Space Complexity O(n)");
                return;
            }
        }
    }
    static void maximumIndexSum(){
        /*Input: arr[] = {1, 20, 2, 10}
        Output: 72.We can get 72 by rotating array twice.
        {2, 10, 1, 20}
        20*3 + 1*2 + 10*1 + 2*0 = 72

        Input: arr[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9}
        Output: 330
        We can get 330 by rotating array 9 times.
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        0*1 + 1*2 + 2*3 … 9*10 = 330
        System.out.println("Naive Approach");
        int[] a = { 1,20,2,10 }; int n = a.length;
        int rotate = n-1;  // rotate == n means same array
        int max = Integer.MIN_VALUE;
        while (rotate-- > 0){
            int result = 0;
            for (int i = 0; i < n; i++) {
                result += (a[i]*i);
            }
            if(max < result)
                max = result;
            int temp = a[0];
            for (int i = 0; i < n-1; i++) {
                a[i] = a[i+1];
            }
            a[n-1] = temp;
        }
        System.out.print("Maximum Sum of i*a[i] = "+max);*/
        System.out.println("\nEfficient approach by calculating\n current sum value from previous curVal");
        int[] b = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = b.length;
        int curSum = 0;
        int curVal = 0;
        for (int i = 0; i < n; i++) {
            curSum += b[i];
            curVal += (i * b[i]);
        }
        int maxVal = curVal;
        for (int i = 1; i < n; i++) {
            // + b[i-1] + (n-1) * b[i-1] => b[i-1](1+n-1) = b[i-1]*n
            curVal = curVal - curSum + n * b[i-1];
            maxVal = Math.max(maxVal, curVal);
        }
        System.out.println(maxVal);
    }
    static int binarySearchRotated(int[] a, int low, int high, int key){
        if(high < low)
            return -1;
        int mid = (low+high)/2;
        if(key == a[mid])
            return mid;
        if(a[low] <= a[mid]) {  // Check first half of array is sorted or not
            if(key >= a[low] && key <= a[mid])
                return binarySearchRotated(a, low, mid-1,key);  // if not in first half means go to second
            return binarySearchRotated(a, mid+1,high,key);
        }
        //if(a[mid] <= a[high]) {  // if first half is not sorted means second half must be sorted // eg. 4,5,1,2,3
        if(key >= a[mid] && key <= a[high])
            return binarySearchRotated(a,mid+1, high, key);
        return binarySearchRotated(a, low, mid-1, key);
    }
}
