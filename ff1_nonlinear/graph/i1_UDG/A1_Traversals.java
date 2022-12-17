package com.ff1_nonlinear.graph.i1_UDG;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class A1_Traversals {

    public static void main(String[] args) {

    }

    public static void adjacentMatrix() {

        int n = 3, m = 3;

        int[][] adj = new int[n + 1][n + 1];

        // edge 1---2

        adj[1][2] = 1;
        adj[2][1] = 1;

        // edge 2---3

        adj[2][3] = 1;
        adj[3][2] = 1;

        // edge 1--3

        adj[1][3] = 1;
        adj[3][1] = 1;


    }

    public static void adjacentList() {

        int n = 3, m = 3;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // edge 1---2

        adj.get(1).add(2);
        adj.get(2).add(1);

        // edge 2---3

        adj.get(2).add(3);
        adj.get(3).add(2);

        // edge 1---3

        adj.get(1).add(3);
        adj.get(3).add(1);

        System.out.println(adj);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {

            Integer node = queue.poll();
            bfs.add(node);

            for (Integer it : adj.get(node)) {  // this loops runs unto the degree of edge
                if (!visited[it]) {
                    visited[it] = true;
                    queue.add(it);
                }
            }
        }
        return bfs;

        // time  -> O(N) + O(2V)  -> O(N) for queue empty and O(2V) twice of edges
        // space -> O(3N) ~ O(N)
    }

    public static ArrayList<Integer> dfs(int V, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> list = new ArrayList<>();   // time complexity -> O(N + 2E);
        boolean[] visited = new boolean[V];           //  sc -> 2 O(N) + O(N)  ~ O(N)
        visited[0] = true;

        dfsFunc(0, visited, adj, list);
        return list;
    }
    private static void dfsFunc(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> list) {

        visited[node] = true;  // u can do this in foreach loop also
        list.add(node);

        for (Integer it : adj.get(node)) {
            if (!visited[it]) {
                dfsFunc(it, visited, adj, list);
            }
        }
    }
}
