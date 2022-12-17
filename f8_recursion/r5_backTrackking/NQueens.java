package com.f8_recursion.r5_backTrackking;

public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        int possibleWays = nQueens(board, 0);
        System.out.println(possibleWays);
    }
    // N Queens

    static int nQueens(boolean[][] board, int row){
        if(row == board.length){ // this will execute whenever all queens are placed correctly becoz it didn't placed correctly we can't reach end of row
            display(board);
            return 1;
        }
        int count = 0;
        // placing the queen and check for every row and column
        for (int col = 0; col < board.length; col++) {
            // place the queen , if it is safe
            if(isSafe(board, col, row)){
                board[row][col] = true;
                count += nQueens(board, row+1);
                board[row][col] = false; // if it's a possible way means , this will execute after row == length of board
            }
        }
        return count;
    }

    private static boolean isSafe(boolean[][] board, int col, int row) {
        // check for vertical
        for (int i = 0; i < row; i++) {
            if(board[i][col])
                return false;
        }
        // check for diagonal left
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if(board[row-i][col-i])
                return false;
        }
        // check for diagonal right
        int maxRight = Math.min(row, board.length-1-col);
        for (int i = 1; i <= maxRight; i++) {
            if(board[row-i][col+i])
                return false;
        }
        return true;
    }

    private static void display(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean queen : row) {
                if(queen)
                    System.out.print("Q ");
                else
                    System.out.print("x ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
