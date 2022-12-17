package com.ff1_nonlinear.graph.i2_DAG;

import java.util.*;

public class Directed {

    public static void main(String[] args) {
        safeNodes();
    }

    public boolean isCycleDirected(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[V];
        boolean[] pathVis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i] && detectCycle(adj, i, vis, pathVis)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(ArrayList<ArrayList<Integer>> adj, int node, boolean[] vis, boolean[] pathVis) {

        vis[node] = true;
        pathVis[node] = true;

        for (Integer it : adj.get(node)) {

            if (!vis[it] && detectCycle(adj, it, vis, pathVis)) return true; // when it (node) is not visited
            else if (pathVis[it]) return true;

            // if node has been previously visited, but it has to be visited on the same path
        }
        pathVis[node] = false;  // not correct path, so backtrack
        return false;
    }

    public static void safeNodes() {
        int[][] graph = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        List<Integer> list = eventualSafeNodes(graph);
        System.out.println(list);
    }

    enum State {
        SAFE, UNSAFE
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;
        List<Integer> list = new ArrayList<>(n);
        State[] states = new State[n];

        for (int node = 0; node < n; node++) {
            if (isSafe(node, graph, states)) {
                list.add(node);
            }
        }
        return list;
    }

    private static boolean isSafe(int node, int[][] graph, State[] states) {

        if (states[node] != null)  // if not null means its already visited check whether its safe or not
            return states[node] == State.SAFE;

        states[node] = State.UNSAFE;

        for (int next : graph[node]) {
            if (!isSafe(next, graph, states)) return false;
        }
        states[node] = State.SAFE;
        return true;
    }

    // normal approach

    public static List<Integer> safeNodes(int[][] graph) {

        int n = graph.length;
        boolean[] vis = new boolean[n];
        boolean[] pathVis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i])
                findSafe(i, graph, vis, pathVis);
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < pathVis.length; i++) {
            if (!pathVis[i]) list.add(i);
        }
        return list;
    }

    private static boolean findSafe(int node, int[][] graph, boolean[] vis, boolean[] pathVis) {

        vis[node] = true;
        pathVis[node] = true;

        for (int next : graph[node]) {
            if (!vis[next] && !findSafe(next, graph, vis, pathVis)) return false;
            else if (pathVis[next]) return false;  // refers cycle , safe nodes is not in part of cycle
        }
        pathVis[node] = false;
        return true;
    }
}
