package com.ff1_nonlinear.graph.i1_UDG;

import com.ff1_nonlinear.graph.i3_ShortestPath.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class P6_Lands {

    public static void main(String[] args) {

    }

    public static void landSea() {

        // 100% same regions, previous problem
        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        int enclaves = numEnclaves(grid);
        System.out.println(enclaves);
    }

    public static int numEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int enclaves = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    if (grid[i][j] == 1) {
                        dfs(i, j, grid, vis);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) enclaves++;
            }
        }
        return enclaves;
    }

    private static void dfs(int i, int j, int[][] grid, boolean[][] vis) {

        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1 && !vis[i][j]) {
            vis[i][j] = true;
            dfs(i - 1, j, grid, vis);
            dfs(i + 1, j, grid, vis);
            dfs(i, j - 1, grid, vis);
            dfs(i, j + 1, grid, vis);
        }
    }

    // bfs
    public static int enclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int enclaves = 0;

        Queue<com.ff1_nonlinear.graph.i3_ShortestPath.Pair> queue = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < m; col++) {

                if (row == 0 || row == n - 1 || col == 0 || col == m - 1) {

                    if (grid[row][col] == 1) {
                        queue.offer(new com.ff1_nonlinear.graph.i3_ShortestPath.Pair(row, col));
                        vis[row][col] = true;
                    }
                }
            }
        }
        int[] dir = {0, 1, 0, -1, 0};

        while (!queue.isEmpty()) {

            int r = queue.peek().first;
            int c = queue.poll().second;

            for (int i = 0; i < 4; i++) {

                int nr = r + dir[i];    // nr - next row, nc - next col
                int nc = c + dir[i + 1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && grid[nr][nc] == 1) {
                    queue.offer(new Pair(nr, nc));
                    vis[nr][nc] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) enclaves++;
            }
        }
        return enclaves;
    }
}
