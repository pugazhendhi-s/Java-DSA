package com.f8_recursion.r5_backTrackking;

public class SudokuSolver {
    // LeetCode char[][] board
    static class LCSudoku {
        public static void main(String[] args) {
            char[][] board = {
                    {'5','3','.','.','7','.','.','.','.'},
                    {'6','.','.','1','9','5','.','.','.'},
                    {'.','9','8','.','.','.','.','6','.'},
                    {'8','.','.','.','6','.','.','.','3'},
                    {'4','.','.','8','.','3','.','.','1'},
                    {'7','.','.','.','2','.','.','.','6'},
                    {'.','6','.','.','.','.','2','8','.'},
                    {'.','.','.','4','1','9','.','.','5'},
                    {'.','.','.','.','8','.','.','7','9'}
            };
            boolean sudokuSolved = solve(board);
            if (sudokuSolved)
                display(board);
            else
                System.out.println("Not solved");
        }
        public static boolean solve(char[][] board){
            int n = board.length;
            boolean allFilled = true;
            int row, col=0;
            outer:
            for (row = 0; row < n; row++) {
                for (col = 0; col < n; col++) {
                    if(board[row][col] == '.'){
                        allFilled = false;
                        break outer;
                    }
                }
            }
            if(allFilled)
                return true;
            for (char number = '1'; number <= '9'; number++) {
                if(isSafe(board, row, col, number)){
                    board[row][col] = number;
                    allFilled = solve(board);
                    if(allFilled)
                        return true;
                    else
                        board[row][col] = '.';
                }
            }
            return false;

        }

        private static void display(char[][] board) {
            for (char[] row : board) {
                for (char num : row){
                    System.out.print(num+' ');
                }
                System.out.println();
            }
            System.out.println();
        }

        private static boolean isSafe(char[][] board, int row, int col, char num){
            for (int i = 0; i < board.length; i++) {
                if (board[row][i] == num) {
                    return false;
                }
            }
            for (char[] rows : board) {
                if(rows[col] == num)
                    return false;
            }
            int sqrt = (int)Math.sqrt(board.length);
            int rowStart = row - row % sqrt;
            int colStart = col - col % sqrt;
            for (int r = rowStart; r < rowStart + sqrt; r++) {
                for (int c = colStart; c < colStart + sqrt; c++) {
                    if(board[r][c] == num)
                        return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        if(solve(board))
            display(board);
        else
            System.out.println("Sudoku can't be solved");
    }

    public static boolean solve(int[][] board){
        int n = board.length;
        boolean allFilled = true; // there is no zero  values in that sudoku
        int row, col = 0; // need to initialise col becoz if row == n, means col may not been initialised
        outer:
        for (row = 0; row < n; row++) {
            for (col = 0; col < n; col++) {
                if(board[row][col] == 0){
                    allFilled = false;
                    break outer;
                    // if you find any empty element in the board, then break and check that index is safer or not
                }
            }
        }
        if(allFilled)
            return true; // becoz all the values been filled and sudoku is solved
        // backtrack
        for (int number = 1; number <= 9; number++) {
            if(isSafe(board, row, col, number)){
                board[row][col] = number;
                allFilled = solve(board); // next recursion check for next empty value of sudoku
                if(allFilled)
                    return true;  // it will execute when all the sudoku is solved
                else
                    board[row][col] = 0; // backtrack from last cycle
            }
        }
        return false;

    }

    private static void display(int[][] board) {
        for (int[] row : board) {
            for (int number : row){
                System.out.print(number+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isSafe(int[][] board, int row, int col, int num){
        // check all the rows
        for (int i = 0; i < board.length; i++) {
            // check if the number is in the row
            if (board[row][i] == num) {
                return false;
            }
        }
        // check all the columns
        for (int[] ints : board) {
            // check if the number is in the column or not
            if(ints[col] == num)
                return false;
        }
        // check 3x3 box
        int sqrt = (int)Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;
        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if(board[r][c] == num)
                    return false;
            }
        }
        return true;
    }
}
