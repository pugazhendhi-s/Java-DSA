package com.ff1_nonlinear.graph.i1_UDG;

import java.util.ArrayList;

public class P1_Provinces {
    public static void main(String[] args) {

    }
    public static int noOfProvinces(int[][] adj) {

        int count = 0;
        int V = adj.length;

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int i, int[][] adj, boolean[] vis) {

        for (int j = 0; j < adj.length; j++) {
            if (i != j && adj[i][j] == 1 && !vis[j]) {  // i != j self nodes are not considered
                vis[j] = true;
                dfs(j, adj, vis);
            }
        }
    }



    // another approach
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {

        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjLs.add(new ArrayList<>());
        }
        // changing adjacency ,matrix to Adjacent list
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && adj.get(i).get(j) == 1) {
                    adjLs.get(i).add(j);
                }
            }
        }
        int provinces = 0;
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {

            if (!vis[i]) {
                provinces++;
                dfs(i, adjLs, vis);
            }
        }
        return provinces;
    }

    static void dfs(int node, ArrayList<ArrayList<Integer>> adjLs, boolean[] vis) {

        vis[node] = true;
        for (Integer it : adjLs.get(node)) {
            if (!vis[it]) {
                dfs(it, adjLs, vis);
            }
        }
    }
}
