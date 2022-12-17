package com.ff1_nonlinear.graph.i6_otherAlgos;

import java.util.ArrayList;
import java.util.Arrays;

public class Last {

    static int isBridge(int V, ArrayList<ArrayList<Integer>> adj, int c, int d) {

        int[] tin = new int[V];
        Arrays.fill(tin, -1);
        int[] low = new int[V];
        Arrays.fill(tin, -1);

        boolean[]  vis = new boolean[V];


        for (int i=0; i<V; i++) {

            if ( !vis[i]) {
                if (dfs(i, -1, c, d, tin, low, 0, vis, adj))
                    return 1;
            }
        }
        return 0;
    }

    static boolean dfs(int node, int parent, int c, int d, int[] tin, int[] low, int timer,
                       boolean[] vis, ArrayList<ArrayList<Integer>> adj) {

        vis[node] = true;
        tin[node] = low[node] = timer++;

        for (int it : adj.get(node)) {

            if(it == parent) continue;

            if (!vis[it]) {
                if(dfs(it, node, c, d, tin, low, timer, vis, adj)) return true;

                low[node] = Math.min(low[node], low[it]);

                if (low[it] > tin[node]) {
                    if ((node == c && it == d) || (node == d && it == c)) return true;
                }
            }
            else {
                low[node] = Math.min(low[node], low[it]);
            }
        }
        return false;
    }

    public static ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[V];
        boolean[] isArt = new boolean[V];

        int[] tin = new int[V];
        int[] low = new int[V];

        int timer = 0;
        for (int i = 0; i < V; i++) {
            if(!vis[i]) dfsArticulation(i, -1, adj, vis, tin, low, isArt, timer);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArt[i]) list.add(i);
        }
        return list;
    }

    private static void dfsArticulation(int node, int parent, ArrayList<ArrayList<Integer>> adj,
                                 boolean[] vis, int[] tin, int[] low, boolean[] isArt, int timer) {

        vis[node] = true;
        tin[node] = low[node] = timer++;
        int individualChild = 0;

        for (int it : adj.get(node)) {

            if (! vis[it]) {
                dfsArticulation(it, node, adj, vis, tin, low, isArt, timer);
                low[node] = Math.min(low[node], low[it]);

                if (low[it] >= tin[node] && parent != -1) {
                    isArt[node] = true;
                }
                individualChild += 1;
            }
            else low[node] = Math.min(low[node], tin[it]);
        }
        if (parent != -1 && individualChild > 1)
            isArt[node] = true;
    }
}
