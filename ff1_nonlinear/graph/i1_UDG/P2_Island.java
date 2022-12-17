package com.ff1_nonlinear.graph.i1_UDG;

import com.ff1_nonlinear.graph.i3_ShortestPath.Pair;

import java.util.*;

public class P2_Island {

    private final static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 4 directions
    private final static int[][] directionsAdj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // directions

    public static void main(String[] args) {

    }

    public static void isLands() {

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int islands = noIslands(grid);
        System.out.println(islands);
    }

    public static int numIslands(char[][] grid) {

        int islands = 0;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    islands++;
                }
            }
        }
        return islands;
    }

    private static void dfs(char[][] grid, int r, int c) {
        // check edge cases
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1')
            return;

        grid[r][c] = '0';

        for (int[] dir : directions) {
            dfs(grid, r + dir[0], c + dir[1]);
        }
    }

    // bfs

    public static int noIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int island = 0;

        for (int r = 0; r < n; r++) {

            for (int c = 0; c < m; c++) {

                if (!vis[r][c] && grid[r][c] == '1') {
                    island++;
                    bfs(r, c, grid, vis);
                }
            }
        }
        return island;
    }

    private static void bfs(int r, int c, char[][] grid, boolean[][] vis) {

        vis[r][c] = true;
        int n = grid.length;
        int m = grid[0].length;

        Queue<com.ff1_nonlinear.graph.i3_ShortestPath.Pair> queue = new LinkedList<>();
        queue.add(new com.ff1_nonlinear.graph.i3_ShortestPath.Pair(r, c));


        while (!queue.isEmpty()) {

            int row = queue.peek().first;
            int col = queue.poll().second;

            for (int[] dir : directions) {

                int nr = row + dir[0];
                int nc = col + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && grid[nr][nc] == '1') {
                    vis[nr][nc] = true;
                    queue.add(new Pair(nr, nc));
                }
            }
        }
    }


    public static void countDistinctIslands() {

        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 0, 1, 0}
        };
        int islands = countDI(grid);
        System.out.println(islands);
    }

    public static int countDI(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        Set<ArrayList<String>> set = new HashSet<>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (grid[row][col] == 1 && !vis[row][col]) {
                    ArrayList<String> list = new ArrayList<>();
                    dfsDi(row, col, grid, vis, list, row, col);
                    set.add(list);
                }
            }
        }
        return set.size();
    }

    public static void dfsDi(int row, int col, int[][] grid, boolean[][] vis,
                             ArrayList<String> list, int x, int y) {

        if (row >= 0 && row < grid.length && col >= 0
                && col < grid[0].length && grid[row][col] == 1 && !vis[row][col]) {

            vis[row][col] = true;

            list.add((row - x) + "" + (col - y));

            dfsDi(row - 1, col, grid, vis, list, x, y);
            dfsDi(row + 1, col, grid, vis, list, x, y);
            dfsDi(row, col - 1, grid, vis, list, x, y);
            dfsDi(row, col + 1, grid, vis, list, x, y);
        }
    }
}
