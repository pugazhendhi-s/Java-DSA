package com.ff1_nonlinear.graph.i1_UDG;

import java.util.*;

public class P4_DetectCycle {

    private final static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 4 directions
    private final static int[][] directionsAdj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // directions


    public static void main(String[] args) {

    }

    // detect cycle in undirected graph

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i] && BFS(adj, i, vis)) {
                return true;
            }
        }
        return false;
    }

    private boolean DFS(ArrayList<ArrayList<Integer>> adj, int node, int parent, boolean[] vis) {

        vis[node] = true;

        for (Integer it : adj.get(node)) {

            if (!vis[it]) {
                vis[it] = true;
                if (DFS(adj, it, node, vis)) return true;
            } else if (parent != it) return true;
        }
        return false;
    }

    // bfs
    private boolean BFS(ArrayList<ArrayList<Integer>> adj, int source, boolean[] vis) {

        vis[source] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{source, -1});

        while (!queue.isEmpty()) {

            int node = queue.peek()[0]; // source node and its parent
            int par = queue.poll()[1];

            for (Integer it : adj.get(node)) {

                if (!vis[it]) {
                    vis[it] = true;
                    queue.offer(new int[]{it, node});
                } else if (par != it) return true;
            }
        }
        return false;
    }

    // similar to detect cycle

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {

        int[] colored = new int[V];
        Arrays.fill(colored, -1);

        for (int i = 0; i < V; i++) {
            if (colored[i] == -1 && !validColor(i, colored, adj, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean validColor(int node, int[] colored, ArrayList<ArrayList<Integer>> adj, int paint) {

        colored[node] = paint;

        for (Integer it : adj.get(node)) {

            if (colored[it] == -1) {
                if (!validColor(it, colored, adj, 1 - paint)) return false;
            } else if (colored[it] == paint) return false;
        }
        return true;
    }

    // matrix
    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] colored = new int[n];

        for (int i = 0; i < n; i++) {

            if (colored[i] == 0 && notValid(i, colored, graph, 1)) return false;
        }
        return true;
    }

    private boolean notValid(int node, int[] colored, int[][] graph, int paint) {

        colored[node] = paint;

        for (Integer it : graph[node]) {

            if (colored[it] == 0) {
                if (notValid(it, colored, graph, paint * -1)) return true;  // not bipartite
            } else if (colored[it] == paint) return true;
        }
        return false;
    }

}
