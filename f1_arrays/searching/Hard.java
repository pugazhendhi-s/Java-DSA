package com.f1_arrays.searching;

public class Hard{
        static void divideChocolate(){

            int[] a = {1,2,2,1,2,2,1,2,2};
            int k = 2;
            int start = Integer.MAX_VALUE;
            int end = 0;
            for (int x : a) {
                start = Math.min(start, x);
                end += x;
            }
            while (start <= end){
                int mid = start + (end -start)/2;
                if(canSplitChoco(a,k,mid))
                    start = mid+1;
                else
                    end = mid-1;
            }
            System.out.println(end);
        }
        static boolean canSplitChoco(int[] a, int k, int target){
            int sum = 0; int pieces = 0;
            for (int x : a) {
                sum += x;
                if(sum >= target){
                    sum = 0;
                    pieces++;
                }
            }
            return pieces >= k+1;
        }
        static void findMedian(){
            int[] a = {1,2};
            int[] b = {3,4};
            System.out.println(findMedianSortedArrays(a,b));
        }
        static double findMedianSortedArrays(int[] a, int[] b) {
            // make sure A is shorter
            int n1 = a.length, n2 = b.length;
            if(n1 > n2)
                return findMedianSortedArrays(b ,a);  // swap
            int start = 0, end = n1;
            int size = n1+n2;    // total size of two array
            int mid = (size)/2; // total mid of two array
            while(start <= end){
                int cut1 = (start + end)/2; // a-> mid
                int cut2 = mid - cut1;  // b -> mid

                int la = (cut1 == 0)       ?  Integer.MIN_VALUE : a[cut1-1] ;
                int lb = (cut2 == 0)       ?  Integer.MIN_VALUE : b[cut2-1] ;
                int ra = (cut1 == n1)      ?  Integer.MAX_VALUE : a[cut1]   ;
                int rb = (cut2 == n2)      ?  Integer.MAX_VALUE : b[cut2]   ;

                if(la > rb)
                    end = cut1-1;
                else if(lb > ra)
                    start = cut1+1;
                else{
                    if(size % 2 == 1)
                        return Math.min(ra,rb);
                    return (double) (Math.max(la,lb)+Math.min(ra,rb))/2;
                }
            }
            return -1;
        }
    }