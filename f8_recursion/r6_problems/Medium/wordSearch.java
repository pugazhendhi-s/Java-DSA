package com.f8_recursion.r6_problems.Medium;

public class wordSearch {
    /** 79. Word Search
     * Given an m x n grid of characters board and a string word,
     * return true if word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     */

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String target = "ABCCEDFSA";
        boolean exist = exist(board, target);
        System.out.println(exist);
    }
    public static boolean exist(char[][] board, String tar) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                boolean exist = isExits(tar, 0, r, c, board);
                if(exist)
                    return true;
            }
        }
        return false;
    }
    private static boolean isExits(String tar, int index ,int r, int c, char[][] board) {
        if(index == tar.length())
            return true;
        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return false;
        if(board[r][c] != tar.charAt(index))
            return false;
        char ch = board[r][c];
        board[r][c] = '.';
        boolean exists = isExits(tar, index+1, r+1, c, board) ||
                isExits(tar, index+1, r, c+1, board) ||
                isExits(tar, index+1, r-1, c, board) ||
                isExits(tar, index+1, r, c-1, board);
        board[r][c] = ch;

        return exists;
    }

    // using dfs , don't write conditions all time.
    public static boolean existDFS(char[][] board, String target) {
        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if(dfs(target, 0, r, c, board, directions))
                    return true; // this will execute after index == target.length
            }
        }
        return false;
    }
    private static boolean dfs(String target, int index, int r, int c, char[][] board, int[][] directions) {
        if(index == target.length())
            return true;
        if(r < 0 || c < 0 || r == board.length || c == board[0].length)
            return false;
        if(board[r][c] == '.' || board[r][c] != target.charAt(index))
            return false;
        char current = board[r][c];
        board[r][c] = '.';
        for (int[] dirs : directions) {
            int newRow = r + dirs[0]; int newCol = c + dirs[1];
            if(dfs(target, index+1, newRow, newCol, board, directions))
                return true;
        }
        board[r][c] = current;
        return false;
    }
}
