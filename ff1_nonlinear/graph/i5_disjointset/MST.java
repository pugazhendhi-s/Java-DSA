package com.ff1_nonlinear.graph.i5_disjointset;

import java.util.*;

public class MST {

    public static void main(String[] args) {

        removeStones();
    }

    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        List<Edge> edges = new ArrayList<>();
        // time -> O(M+N)
        for (int u = 0; u < V; u++) {
            for (ArrayList<Integer> list : adj.get(u)) {

                int v = list.get(0);
                int wt = list.get(1);

                Edge newEdge = new Edge(u, v, wt);
                edges.add(newEdge);
            }
        }

        DisjointSet ds = new DisjointSet(V);

        // time -> M logM
        Collections.sort(edges);
        int mstWt = 0;

        // M * 4 * alpha * 2 -> (2 * 4lp) because findParent for both u, v
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            int wt = edge.weight;

            int pu = ds.findParent(u);
            int pv = ds.findParent(v);

            if (pu != pv) {
                mstWt += wt;
                ds.unionBySize(u, v);
            }
        }
        return mstWt;
    }

    public static int numOfProvinces(int[][] adj) {

        DisjointSet ds = new DisjointSet(adj.length);

        for (int i=0; i<adj.length; i++) {
            for (int j=0; j<adj[0].length; j++) {

                if (adj[i][j] == 1) {
                    ds.unionBySize(i, j);
                }
            }
        }

        int ulp = 0;
        for (int i=0; i<adj.length; i++) {
            if(ds.findParent(i) == i) ulp++;
        }
        return ulp;
    }


    public static int makeConnected(int n, int[][] connections) {

        if (n - 1 > connections.length) return -1; // this can be solved by dfs also by creating adjacent list
        int edges = n - 1;  // minimum n-1 edges to connect n nodes

        DisjointSet ds = new DisjointSet(n);

        for (int[] c : connections) {

            int pu = ds.findParent(c[0]);
            int pv = ds.findParent(c[1]);

            if (pu != pv) {
                ds.unionBySize(pu, pv);
                edges--; // we are using one edge to connect pu, pv
            }
        }
        return edges;
    }

    // inline approach without using disjoint class explicitly
    public static int makeConnectedInLine(int n, int[][] connections) {

        if (n - 1 > connections.length) return -1;

        int[] par = new int[n];

        int edges = n - 1; // minimum n-1 edges to connect n nodes

        for (int i = 0; i < n; i++) par[i] = i;

        for (int[] con : connections) {

            int pu = findParent(par, con[0]);
            int pv = findParent(par, con[1]);

            if (pu != pv) {
                par[pv] = pu;
                edges--;
            }
        }
        return edges;
    }
    public static int findParent(int[] par, int node) {

        if (node == par[node]) return node;

        return par[node] = findParent(par, par[node]);
    }


    public static void removeStones() {

        int[][] stones = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        int removes = removeStones(stones);
        System.out.println(removes);
    }
    public static int removeStones(int[][] stones) {

        int maxRow = 0;
        int maxCol = 0;
        int n = stones.length;
        for (int[] st : stones) {
            maxRow = Math.max(maxRow, st[0]);
            maxCol = Math.max(maxCol, st[1]);
        }

        // total nodes maxRow, maxCol, we add one for precaution of  out of bounds exception

        int m = maxRow + maxCol + 1;

        DisjointSet ds = new DisjointSet(m);
        Map<Integer, Integer> stoneNodes = new HashMap<>();

        for (int[] st : stones) {
            int nodeRow = st[0];
            int nodeCol = st[1] + maxRow + 1;
            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int ulp = 0;

        for (Map.Entry<Integer, Integer> it : stoneNodes.entrySet()) {

            if (ds.findParent(it.getKey()) == it.getKey()) ulp++;
        }
        return n - ulp;
    }


    public List<Integer> numOfIslands(int n, int m, int[][] operators) {

        List<Integer> list = new ArrayList<>();
        boolean[][] vis = new boolean[n][m];

        int island = 0; // to count islands

        DisjointSet ds = new DisjointSet(n * m);  // we create node for all indexes (n * m)

        for (int[] op : operators) {
            int r = op[0];
            int c = op[1];

            if (vis[r][c]) {       // if already visited means , then directly add that island
                list.add(island);
                continue;
            }

            vis[r][c] = true;
            island++;

            int[] dir = {1, 0, -1, 0, 1};

            for (int i=0; i<4; i++) {
                int adjR = r + dir[i];
                int adjC = c + dir[i+1];

                if (isValid(adjR, adjC, n, m) && vis[adjR][adjC]) {    // if vis means it may share parent with that adj
                    int nodeNo = r * m + c;
                    int adjNodeNo = adjR * m + adjC;

                    if (ds.findParent(nodeNo) != ds.findParent(adjNodeNo))  {
                        ds.unionBySize(nodeNo, adjNodeNo);
                        island--;
                        // if both are adjacent , but its parent not same
                        // so make them same and join island, then reduce island count
                    }
                }
            }
            list.add(island);
        }
        return list;
    }
    private boolean isValid(int r, int c, int n, int m) {
        return (r >= 0 && r < n && c >= 0 && c < m);
    }


    public static List<List<String>> accountsMerge(List<List<String>> accounts) {

        HashMap<String, List<String>> adjacentList = new HashMap<>();
        HashSet<String> vis = new HashSet<>();

        for (List<String> acc : accounts) {
            int size = acc.size();

            String firstMail = acc.get(1);
            for (int j = 2; j < size; j++)  {
                String mail = acc.get(j);

                adjacentList .putIfAbsent(firstMail, new ArrayList<>());
                adjacentList .get(firstMail).add(mail);

                adjacentList .putIfAbsent(mail, new ArrayList<>());
                adjacentList .get(mail).add(firstMail);
            }
        }

        List<List<String>> mergeAcc = new ArrayList<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            String firstMail = acc.get(1);

            if(!vis.contains(firstMail)) {
                List<String> merge = new ArrayList<>();
                merge.add(name);

                dfs(merge, firstMail, adjacentList, vis);

                Collections.sort(merge.subList(1, merge.size()));
                mergeAcc.add(merge);
            }
        }
        return mergeAcc;
    }
    private static void dfs(List<String> merge, String mail, HashMap<String, List<String>> adjacentList, HashSet<String> vis) {

        vis.add(mail);
        merge.add(mail);

        if (!adjacentList .containsKey(mail)) return;

        for (String neigh : adjacentList .get(mail)) {
            if (!vis.contains(neigh)) {
                dfs(merge, neigh, adjacentList, vis);
            }
        }
    }

    // accounts merge unionfind

    public static List<List<String>> accountsMergeUnion(List<List<String>> accounts) {

        Map<String, Integer> emailGroup = new HashMap<>();
        int accountsSize = accounts.size();
        DisjointSet ds = new DisjointSet(accountsSize);

        for (int i=0; i<accountsSize; i++) {

            for (int j = 1; j < accounts.get(i).size(); j++) {

                String email = accounts.get(i).get(j);

                if (!emailGroup.containsKey(email)) {
                    emailGroup.put(email, i);
                }
                else {
                    ds.unionBySize(i, emailGroup.get(email));
                }
            }
        }

        Map<Integer, List<String>> components = new HashMap<>();

        for (String email : emailGroup.keySet()) {

            int group = emailGroup.get(email);
            int groupRep = ds.findParent(group);

            components.putIfAbsent(groupRep, new ArrayList<>());
            components.get(groupRep).add(email);
        }

        List<List<String>> merge = new ArrayList<>();

        for (int group : components.keySet()) {

            List<String> list = components.get(group);
            Collections.sort(list);
            list.add(0, accounts.get(group).get(0));

            merge.add(list);
        }
        return merge;
    }

    public int largestIsland(int[][] grid) {

        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        int[] dir = {1, 0, -1, 0, 1};

        for (int i=0; i<n; i++) {

            for (int j=0; j<n; j++) {

                if(grid[i][j] == 0) continue;

                for (int k=0; k<4; k++) {

                    int ii = i + dir[k];
                    int jj = j + dir[k+1];

                    if(ii < 0 || jj < 0 || ii >= n || jj >= n || grid[ii][jj] == 0)
                        continue;

                    int node = i * n + j;
                    int adjNod = ii * n + jj;

                    ds.unionBySize(node, adjNod);
                }
            }
        }

        int maxIsland = 0;

        for (int i=0; i<n; i++) {

            for (int j=0; j<n; j++) {

                if (grid[i][j] == 1) continue;

                HashSet<Integer> components = new HashSet<>();
                int island = 0;

                for (int k=0; k<4; k++) {

                    int ii = i + dir[k];
                    int jj = j + dir[k+1];

                    if (ii < 0 || jj < 0 || ii >= n || jj >= n || grid[ii][jj] == 0)
                        continue;

                    int adjNod = ii * n + jj;
                    int parent = ds.findParent(adjNod);

                    if(components.add(parent))
                        island += ds.getSize(parent);
                }
                maxIsland = Math.max(maxIsland, island+1);
            }
        }
        for (int cell=0; cell < n*n; cell++) {  // if all the cells are 1
            int par = ds.findParent(cell);
            maxIsland = Math.max(maxIsland, ds.getSize(par));
        }
        return maxIsland;
    }

}
