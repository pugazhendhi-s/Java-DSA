package com.ff1_nonlinear.graph.i3_ShortestPath;

import java.util.*;

public class ShortestPath {

    public static void main(String[] args) {

    }
    public static int shortestPath(int[][] grid, int[] src, int[] dtn) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(src);

        boolean[][] vis = new boolean[n][m];
        vis[src[0]][src[1]] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int len = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i=0; i<size; i++) {

                assert queue.peek() != null;
                int r = queue.peek()[0];
                int c = queue.poll()[1];

                if(r == dtn[0] && c == dtn[1]) return len;

                for(int[] dir : directions) {

                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= m || vis[nr][nc] || grid[nr][nc] == 0)
                        continue;

                    queue.offer(new int[] {nr, nc});
                    vis[nr][nc] = true;
                }
            }

            len++;
        }
        return -1;
    }
    // undirected graph
    public int[] shortestPath(int[][] edges, int N, int M, int src) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i=0; i<N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, (int)1e9);

        dist[src] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v : adj.get(u)) {

                if (dist[u] + 1 < dist[v]) {
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                }
            }
        }

        for (int i=0; i<N; i++) {
            if(dist[i] == 1e9) dist[i] = -1;
        }

        return dist;
    }

    // directed graph
    public static int[] shortestPath(int N, int M, int[][] edges) {
        // N -> number of vertices, M -> No of edges
        List<List<Pair>> adj = new ArrayList<>();

        for (int i=0; i<N; i++) adj.add(new ArrayList<>());

        for (int i=0; i<M; i++) {

            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            adj.get(u).add(new Pair(v, w));
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[N];

        // dfs topoSort [T -> O(N+M)]
        for (int i=0; i<N; i++) if(!vis[i]) topoSort(i, vis, adj, stack);

        int[] dist = new int[N];
        Arrays.fill(dist, (int)1e9);
        dist[0] = 0;

        // outer loop O(N), inner loop executes at M times [T -> (N+M)]
        while (!stack.empty()) {
            int u = stack.pop();

            for (Pair p : adj.get(u)) {
                int v = p.v;
                int w = p.w;

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        for (int i=0; i<N; i++) if(dist[i] == 1e9) dist[i] = -1;
        return dist;
    }

    static class Pair {

        int v;
        int w;

        Pair (int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void topoSort(int u, boolean[] vis, List<List<Pair>> adj, Stack<Integer> stack) {

        vis[u] = true;

        for (Pair p : adj.get(u)) {
            if(!vis[p.v])
                topoSort(p.v, vis, adj, stack);
        }
        stack.push(u);
    }
}


