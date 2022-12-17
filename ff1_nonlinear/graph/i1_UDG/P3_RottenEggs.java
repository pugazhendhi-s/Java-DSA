package com.ff1_nonlinear.graph.i1_UDG;

import java.util.LinkedList;
import java.util.Queue;

public class P3_RottenEggs {

    private final static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 4 directions
    private final static int[][] directionsAdj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // directions

    public static void main(String[] args) {

    }

    public static void rottenEggs() {

        int[][] grid = {

                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1},
        };
        int minutes = orangeRotting(grid);
        System.out.println(minutes);
    }

    public static int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int minutes = 0;

        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                if (grid[r][c] == 1) fresh++;
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
            }
        }

        while (fresh > 0 && !queue.isEmpty()) {

            minutes++;
            int size = queue.size();

            while (fresh > 0 && size-- > 0) {
                int[] point = queue.remove();

                for (int[] dir : directions) {

                    int row = point[0] + dir[0];
                    int col = point[1] + dir[1];

                    if (row >= 0 && row < n && col >= 0 && col < m && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        queue.offer(new int[]{row, col});
                        fresh--;
                    }
                }
            }
        }
        return (fresh == 0) ? minutes : -1;
    }

    public static int orangeRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] vis = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                if (grid[r][c] == 1) fresh++;
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c, 0});
                    vis[r][c] = 2;
                } else vis[r][c] = 0;
            }
        }
        int tn = 0;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            int[] point = queue.poll();

            tn = point[2];

            for (int i = 0; i < 4; i++) {

                int row = point[0] + dr[i];
                int col = point[1] + dc[i];

                if (row >= 0 && row < n && col >= 0 && col < m && vis[row][col] == 0 && grid[row][col] == 1) {

                    vis[row][col] = 2;
                    queue.offer(new int[]{row, col, point[2] + 1});
                    fresh--;
                }
            }
        }
        return (fresh == 0) ? tn : -1;
    }

}
