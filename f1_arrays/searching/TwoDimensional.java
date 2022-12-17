package com.f1_arrays.searching;
import java.util.*;

public class TwoDimensional{  // Binary Search
    public static void main(String[] args) {

    }
    static void rowColSorted(){
        int[][] a = {
                {10, 20, 30, 40},  // row and column vise sorted
                {14, 28, 44, 50},
                {22, 31, 47, 54},
                {35, 37, 49, 60}
        };
        int target = 47;
        int row = 0; int col = a.length-1;
        while (row < a.length && col >= 0){
            if(a[row][col] == target)
                break;
            else if(a[row][col] > target)
                col--;
            else
                row++;
        }
        if(col == -1)
            System.out.println("Element not found");
        else
            System.out.printf("(row,col) = (%d, %d) = %d",row, col, a[row][col]);
    }
    static void strictlySortedMatrix(){
        /*
         *Take middle column and middle row and perform bs
         */
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int target = 1;
        long start = System.nanoTime();

        int[] index = searchInSortedMatrix(matrix, target);
        long end = System.nanoTime();
        long res = end-start;
        System.out.println(Arrays.toString(index) +"\nTime Taken for Binary Search = "+res);

        // native approach
        start = System.nanoTime();
        outer:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == target){
                    System.out.println("["+i+", "+j+"]");
                    break outer;
                }
            }
        }
        end = System.nanoTime();
        res = end-start;
        System.out.println("Time taken for native approach = "+res);
    }
    static int[] searchInSortedMatrix(int[][] matrix, int target){
        int row = matrix.length;
        int col = matrix[0].length;
        /*
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
         */
        // 1. edge case
        if(row == 1)
            return bS(matrix, 0, 0, col-1, target);

        int rowStart = 0; int rowEnd = row-1;
        int colMid = (col/2)-1;
        // 2. Run the loop till 2 rows are remaining  eg. rowStart = 1, rowEnd = 3
        while (rowStart < (rowEnd-1)){
            int rowMid = rowStart + (rowEnd - rowStart)/2;
            if(matrix[rowMid][colMid] == target)
                return new int[] {rowMid, colMid};
            if(matrix[rowMid][colMid] < target)
                rowStart = rowMid;
            else
                rowEnd = rowMid;
        }
        // 3. Now we have 2 rows check the target is in midCol of two rows
        if(matrix[rowStart][colMid] == target)
            return new int[]{rowStart, colMid};
        if(matrix[rowStart+1][colMid] == target)    // only two rows, so rowEnd = rowStart+1;
            return new int[]{rowStart+1, colMid};

        // 4. If not in midCol , then 4 conditions
        // 5. Search in 1st half
        if(matrix[rowStart][colMid-1] >= target)
            return bS(matrix, rowStart, 0, colMid-1, target);
        // 6. Search in 2nd half
        if(matrix[rowStart][colMid+1] <= target && matrix[rowStart][col-1] >= target)
            return bS(matrix, rowStart,colMid+1,col-1, target);
        // 7. Search in 3rd half
        if(matrix[rowStart+1][colMid-1] >= target)
            return bS(matrix,rowStart+1, 0, colMid-1, target);
        // 8. Search in 4th half
        if(matrix[rowStart+1][colMid+1] <= target)
            return bS(matrix,rowStart+1,colMid+1,col-1, target);
        return new int[]{-1, -1};
    }
    static int[] bS(int[][] matrix, int row, int colStart, int colEnd, int target){
        while (colStart <= colEnd){
            int mid = colStart + (colEnd-colStart)/2;
            if(matrix[row][mid] == target)
                return new int[]{row, mid};
            if(matrix[row][mid] > target)
                colEnd = mid-1;
            if(matrix[row][mid] < target)
                colStart = mid+1;
        }
        return new int[]{-1,-1};
    }

    static void searchIn2DArray(){
        int[][] a = {
                {23, 4, 1},
                {18, 12, 3, 9},
                {78, 99, 34, 56},
                {18, 12}
        };
        int key = 34;
        int[] index = search2DFunct(a,key);
        System.out.println("Key 34 in index : "+Arrays.toString(index));
    }
    static int[] search2DFunct(int[][] a, int key){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j] == key)
                    return new int[]{i, j};
            }
        }
        return new int[]{-1,-1};
    }
    static void maxVal2D(){
        int[][] a = {
                {23, 4, 1},
                {18, 12, 3, 9},
                {78, 99, 34, 56},
                {18, 12}
        };
        int max = a[0][0];
        for (int[] x: a) {
            for (int y: x) {
                max = Math.max(max, y);
            }
        }
        System.out.println(max);
    }
}