package com.ff1_nonlinear.graph.i2_DAG;

import java.util.*;


public class DAG {

    public static void main(String[] args) {
        alienDictionary();
    }

    // BFS
    public static int[] khansTopo(int V, List<List<Integer>> adj) {

        int[] inDegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                inDegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] khans = new int[V];
        int i = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            khans[i++] = node;

            // node is in your topo(khans) sort
            // so remove it from the degree

            for (int it : adj.get(node)) {
                inDegree[it]--;
                if (inDegree[it] == 0) queue.offer(it);
            }
        }
        return khans;

        // time -> O(V + E) , like bfs
        // space -> O(N) -> O(N) -> queue, O(N) -> inDegree , O(N) res, 3O(N) ~ O(N)
    }

    // DFS
    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[V];
        int[] topo = new int[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!vis[i]) dfs(i, adj, vis, stack);
        }
        int i = 0;

        while (!stack.empty()) {
            topo[i++] = stack.pop();
        }

        return topo;
    }

    public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> stack) {

        vis[node] = true;

        for (int next : adj.get(node)) {
            if (!vis[next]) dfs(next, adj, vis, stack);
        }
        stack.push(node);
    }
    // 1. detect cycle

    // bfs - khans topo sort O(V+E)
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {

        int[] inDegree = new int[V];

        for (int i = 0; i < V; i++) {

            for (int it : adj.get(i)) {
                inDegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int topo = 0;
        while (!queue.isEmpty()) {

            int node = queue.poll();
            topo++;

            for (int it : adj.get(node)) {
                inDegree[it]--;
                if (inDegree[it] == 0) queue.offer(it);
            }
        }
        return topo != V;  // topo == V, no cycle in it
    }

    // 2. course schedule

    static int[] findOrder(int V, int m, ArrayList<ArrayList<Integer>> prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));
        }

        int[] inDegree = new int[V];

        for (int i = 0; i < V; i++) {

            for (int it : adj.get(i)) {
                inDegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] topo = new int[V];
        int i = 0;
        while (!queue.isEmpty()) {

            int node = queue.poll();
            topo[i++] = node;

            for (int it : adj.get(node)) {
                inDegree[it]--;
                if (inDegree[it] == 0) queue.offer(it);
            }
        }
        if (i == V) return topo;

        return new int[]{};
    }

    // 3.
    // bfs khans
    public static List<Integer> eventualSafeNodes(int[][] adj) {

        int V = adj.length;
        ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }

        int[] outDegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int it : adj[i]) {
                adjRev.get(it).add(i);
                outDegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (outDegree[i] == 0) queue.offer(i);
        }

        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {

            int node = queue.poll();
            list.add(node);

            for (int it : adjRev.get(node)) {
                outDegree[it]--;
                if (outDegree[it] == 0) queue.offer(it);
            }
        }
        Collections.sort(list);
        return list;
    }
    // 4. Alien dict
    public static void alienDictionary() {
        int N = 5, K = 4;
        String[] dict = {"baa","abcd","abca","cab","cad"};
        System.out.println(findOrder(dict, N, K));
    }

    public static String findOrder(String [] dict, int N, int K) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i=0; i<K; i++ ) {
            adj.add(new ArrayList<>());
        }

        // listing adjacent of chars

        for (int i=0; i<N-1; i++) {

            String x = dict[i], y = dict[i+1];
            int len = Math.min(x.length(), y.length());

            for (int j=0; j<len; j++) {

                if (x.charAt(j) != y.charAt(j)) {
                    adj.get(x.charAt(j) - 'a').add(y.charAt(j) - 'a');
                    break;
                }
            }
        }
        int[] topo = khansTopo(K, adj);
        StringBuilder res = new StringBuilder();

        for(int it : topo) {
            res.append((char) (it + (int) ('a')));
        }

        return res.toString();

        /* how to find this is wrong dict (not order, cycle)

            1. [abcd
               abc]  , if order like means, this wrong, shorter always comes first [abc, abcd] is right(normal dict also)

            2. [abcd
               bcda       a <= b< = a this is cyclic
               abdt]


         */

    }
//  course schedule
    public int[] findOrder(int V, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] inDegree = new int[V];

        for (int i=0; i<V; i++) {

            for (int it : adj.get(i)) {
                inDegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i=0; i<V; i++) {
            if(inDegree[i] == 0) queue.offer(i);
        }

        int[] topo = new int[V];
        int i=0;

        while(!queue.isEmpty()) {

            int node = queue.poll();
            topo[i++] = node;

            for (int it : adj.get(node)) {
                inDegree[it]--;
                if(inDegree[it] == 0) queue.offer(it);
            }
        }
        if(i == V) return topo;

        return new int[]{};
    }
}

