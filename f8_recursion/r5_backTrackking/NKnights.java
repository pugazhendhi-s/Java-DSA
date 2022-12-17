package com.f8_recursion.r5_backTrackking;

public class NKnights {
    public static void main(String[] args) {
        int n = 4;
        int knights = 8;
        boolean[][] board = new boolean[n][n];
        knights(board, 0, 0, knights);
    }
    static void knights(boolean[][] board, int row, int col, int knights){
        if(knights == 0){
            display(board);
            return;
        }
        if(row == board.length-1 && col == board.length) // last row and last col
            return;
        if(col == board.length){
            knights(board, row+1, 0, knights);
            return;
        }
        if(isSafe(board, row, col)){
            board[row][col] = true;
            knights(board, row, col+1, knights-1);
            board[row][col] = false;
        }
        // if that column is not safe means, increase that column
        knights(board, row, col+1, knights);
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        if(isValid(board, row - 2, col - 1)){
            if(board[row - 2][col - 1])
                return false;
        }
        if(isValid(board, row - 2, col + 1)){
            if(board[row - 2][col + 1])
                return false;
        }
        if(isValid(board, row - 1, col - 2)){
            if(board[row - 1][col - 2])
                return false;
        }
        if(isValid(board, row - 1, col + 2)){
            return !board[row - 1][col + 2];
            // u can't do like this in previous if check, it may return true without checking this condition
        }
        return true;
    }
    private static boolean isValid(boolean[][] board, int row, int col){  // our checks can't be out of bounds
        return row >= 0 && row < board.length && col >= 0 && col < board.length;
    }
    private static void display(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean queen : row) {
                if(queen)
                    System.out.print("K ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
