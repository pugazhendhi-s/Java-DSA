package com.ff1_nonlinear.graph.i1_UDG;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P5_Regions {

    public static void main(String[] args) {

    }

    public static void distance01() {

        int[][] mat = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        for (int[] vals : updateMatrix(mat)) {
            System.out.println(Arrays.toString(vals));
        }
    }

    public static int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        boolean[][] vis = new boolean[n][m];
        int[][] dis = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                    vis[i][j] = true;
                }
            }
        }
        int[] DIR = {-1, 0, 1, 0, -1};

        while (!queue.isEmpty()) {

            int[] pts = queue.poll();
            int row = pts[0];
            int col = pts[1];
            int steps = pts[2];

            dis[row][col] = steps;

            for (int i = 0; i < 4; i++) {

                int nr = row + DIR[i];
                int nc = col + DIR[i + 1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, steps + 1});
                }
            }
        }
        return dis;
    }

    // 6.
    public static void regions() {

        // check from boundary 'O' and its adjacent are also not become X
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'O'}
        };
        solve(board);

        for (char[] region : board) {
            System.out.println(Arrays.toString(region));
        }
    }

    public static void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    if (board[i][j] == 'O') {
                        dfs(i, j, board, vis);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && !vis[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static void dfs(int i, int j, char[][] board, boolean[][] vis) {

        vis[i][j] = true;

        int n = board.length;
        int m = board[0].length;
        int[] dir = {0, 1, 0, -1, 0};

        for (int k = 0; k < 4; k++) {

            int ii = i + dir[k];
            int jj = j + dir[k + 1];

            if (ii >= 0 && ii < n && jj >= 0 && jj < m && board[ii][jj] == 'O' && !vis[ii][jj])
                dfs(ii, jj, board, vis);
        }
    }
}
