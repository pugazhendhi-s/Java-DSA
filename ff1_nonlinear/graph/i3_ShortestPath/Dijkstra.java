package com.ff1_nonlinear.graph.i3_ShortestPath;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        network();
    }

    public static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.first));

        int[] d = new int[V];
        Arrays.fill(d, (int) 1e9);

        d[S] = 0;
        pq.offer(new Pair(0, S));

        while (!pq.isEmpty()) {

            Pair pair = pq.poll();   // u -> node, du -> node distance
            int du = pair.first;
            int u = pair.second;    // v - adjacent node , dv -> adjacent dist

            for (ArrayList<Integer> list : adj.get(u)) {
                int v = list.get(0);
                int dv = list.get(1);

                if (du + dv < d[v]) {
                    d[v] = du + dv;
                    pq.offer(new Pair(d[v], v));
                }
            }
        }
        return d;
    }

    public static void shortestPath() {

        int[][] edges = {{1, 2, 2}, {2, 5, 5}, {2, 3, 4}, {1, 4, 1}, {4, 3, 3}, {3, 5, 1}};
        int n = 5;
        int m = 6;
        List<Integer> path = shortestPath(n, m, edges);
        System.out.println(path);
    }
    public static List<Integer> shortestPath(int n, int m, int[][] edges) {

        List<List<Pair>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int d = e[2];

            adj.get(u).add(new Pair(d, v));
            adj.get(v).add(new Pair(d, u));
        }

        int[] distance = new int[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distance[i] = (int) 1e9;
            parent[i] = i;
        }

        distance[1] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
        pq.offer(new Pair(0, 1));

        while (!pq.isEmpty()) {

            Pair pair = pq.poll();
            int du = pair.first;
            int u = pair.second;

            for (Pair p : adj.get(u)) {
                int ew = p.first;
                int v = p.second;

                if (du + ew < distance[v]) {
                    distance[v] = du + ew;
                    pq.offer(new Pair(du + ew, v));
                    parent[v] = u;
                }
            }
        }
        List<Integer> path = new ArrayList<>();

        if (distance[n] == (int) 1e9) {
            path.add(-1);
            return path;
        }

        int node = n;
        while (parent[node] != node) {   // at worst case we backtrack all , so it takes linear complexity
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        Collections.reverse(path);
        return path;

        // Time Complexity = O(E logV) + O(N)  //
    }



    public static void minEffPath() {

        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int eff = minimumEffortPath(heights);
        System.out.println(eff);
    }
    private static int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        PriorityQueue<Tuple> minheap = new PriorityQueue<>(Comparator.comparingInt(eff -> eff.first));

        int[][] effort = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(effort[i], (int)1e9);
        }

        effort[0][0] = 0;
        minheap.offer(new Tuple(0, 0, 0));

        while (!minheap.isEmpty()) {

            Tuple tuple = minheap.poll();

            int eff = tuple.first;
            int r = tuple.second;
            int c = tuple.third;

            if (r == n-1 && c == m-1) return eff;

            int[] dir = {1, 0, -1, 0, 1};

            for (int i = 0; i < 4; i++) {

                int nr = r + dir[i];
                int nc = c + dir[i+1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {

                    int newEff = Math.max( eff, Math.abs(heights[nr][nc] - heights[r][c]));

                    if (effort[nr][nc] > newEff) {
                        effort[nr][nc] = newEff;
                        minheap.offer(new Tuple(effort[nr][nc], nr, nc));
                    }
                }
            }
        }
        return 0; // 0 efforts

        // Time complexity = E logV  -> (n * m) * 4 * log(n * m) 4 refers directions
    }
    // DFS + Binary Search
    private static int minEffPath(int[][] heights) {

        int lo = 0; int hi = 1_000_000;

        while (lo < hi) {

            int effort = lo + (hi - lo)/2;

            if (isPathBFS(effort, heights)) hi = effort;
            else lo = effort+1;
        }
        return lo;
    }
    private static boolean isPathBFS(int effort, int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});

        boolean[][] vis = new boolean[n][m];
        vis[0][0] = true;

        while (!queue.isEmpty()) {

            int[] curPos = queue.poll();
            int r = curPos[0], c = curPos[1];

            if (r == n-1 && c == m-1) return true;

            int[] dir = {1, 0, -1, 0, 1};

            for (int i = 0; i < 4; i++) {

                int nr = r + dir[i];
                int nc = c + dir[i+1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc]) {

                    int curEffort = Math.abs(heights[nr][nc] - heights[r][c]);

                    if (curEffort <= effort) {
                        queue.offer(new int[] {nr, nc});
                        vis[nr][nc] = true;
                    }
                }
            }
        }
        return false;
    }


    public static int shortestPathBinaryMatrix(int[][] grid) {
        // https://leetcode.com/problems/shortest-path-in-binary-matrix/
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        int len = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            len++;

            for (int i=0; i<size; i++) {

                int r = queue.peek()[0];
                int c = queue.poll()[1];

                if(r == n-1 && c == r) return len;

                for(int[] dir : directions) {

                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n || vis[nr][nc] || grid[nr][nc] == 1)
                        continue;
                    queue.offer(new int[] {nr, nc});
                    vis[nr][nc] = true;
                }
            }

        }
        return -1;
    }

    public static void cheapestFlight() {

        int[][] flights = {{0,1,5}, {1,2,5}, {0,3,2}, {3,1,2}, {1,4,1}, {4,2,1}};
        int n = 5;
        int src = 0;
        int dst = 2;
        int k = 2;
        int cheap = findCheapestPrice(n, flights, src, dst, k);
        System.out.println(cheap);
    }
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<List<int[]>> adj = new ArrayList<>();

        for (int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] fl : flights) {
            adj.get(fl[0]).add(new int[] {fl[1], fl[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, (int)1e9);

        Queue<Tuple> queue = new LinkedList<>();

        dist[src] = 0;
        queue.offer(new Tuple(0, src, 0));

        // time complexity for Dijkstra -> E logV, but we are not using priorityqueue, so remove logV
        // Tc -> O(E) -> flights.size(); or n
        while (!queue.isEmpty()) {

            Tuple tuple = queue.poll();
            int cost = tuple.first;
            int node = tuple.second;
            int stop = tuple.third;

            if (stop > k) continue;

            for (int[] it : adj.get(node)) {
                int adjNod = it[0];
                int edW = it[1];

                if(cost + edW < dist[adjNod]) {
                    dist[adjNod] =  cost + edW;
                    queue.offer(new Tuple(dist[adjNod], adjNod, stop+1));
                }
            }
        }
        return (dist[dst] == (int)1e9) ? -1 : dist[dst];
    }


    public static int minimumMultiplications(int[] arr, int start, int end) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start, 0});

        int[] dist = new int[1_00_000];
        Arrays.fill(dist, (int)1e9);
        dist[start] = 0;

        while (!queue.isEmpty()) {

            int num = queue.peek()[0];
            int steps = queue.poll()[1];

            if(num == end) return steps;

            for(int it : arr) {
                int curr = (num * it) % 100000;
                if (steps + 1 < dist[curr]) {
                    dist[curr] = steps + 1;
                    queue.offer(new int[] {curr, steps+1});
                }
            }
        }
        return -1;
    }

    public static void countPaths() {

        int[][] road = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},
                            {6,5,1},{2,5,1},{0,4,5},{4,6,2}};

        List<List<Integer>> gfg = new ArrayList<>();
        for(int i=0; i<road.length; i++) {
            gfg.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                gfg.get(i).add(road[i][j]);
            }
        }
        int n = 7;
        int gfgCount = countPaths(n, gfg);
        int leetCount = countPath(n, road);
        System.out.println(gfgCount +" " + leetCount);
    }
    // gfg supported
    public static int countPaths(int n, List<List<Integer>> roads) {

        List<List<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> road : roads) {
            int u = road.get(0);
            int v = road.get(1);
            int t = road.get(2);

            adj.get(u).add(new Pair(t, v));
            adj.get(v).add(new Pair(t, u));
        }

        int[] dist = new int[n];
        int[] ways = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;
        ways[0] = 1;
        int mod = (int)(1e9 + 7);

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
        pq.offer(new Pair(0, 0));

        while (pq.size() != 0) {

            Pair p = pq.poll();
            int du = p.first;     // u - node, du -> node weight, v = adjNode, ew -> edge weight
            int u = p.second;

            for (Pair it : adj.get(u)) {
                int ew = it.first;
                int v = it.second;

                if (ew + du < dist[v]) {
                    dist[v] = ew + du;
                    ways[v] = ways[u];
                    pq.offer(new Pair(dist[v], v));
                }
                else if (ew + du == dist[v]) {
                    ways[v]  = (ways[v] + ways[u]) % mod;
                }
            }
        }
        return ways[n-1] % mod;
    }
    // LeetCode supported
    public static int countPath(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];
            adj.get(u).add(new Pair(d, v));
            adj.get(v).add(new Pair(d, u));
        }

        int[] dist = new int[n];
        int[] ways = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            ways[i] = 0;
        }

        dist[0] = 0;
        ways[0] = 1;
        final int mod = (int)(1e9 + 7);

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
        pq.offer(new Pair(0, 0));

        while (!pq.isEmpty()) {

            Pair p = pq.poll();
            int du = p.first;     // u - node, du -> node weight, v = adjNode, ew -> edge weight
            int u = p.second;

            for (Pair it : adj.get(u)) {
                int ew = it.first;
                int v = it.second;

                if (ew + du < dist[v]) {
                    dist[v] = ew + du;
                    ways[v] = ways[u];
                    pq.offer(new Pair(dist[v], v));
                }
                else if (ew + du == dist[v]) {
                    ways[v]  = (ways[v] + ways[u]) % mod;
                }
            }
        }
        return ways[n-1] % mod;
    }


    public static void network() {

        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1},
        };
        int time = networkDelayTime(times, 4, 2);
        System.out.println(time);
    }
    public static int networkDelayTime(int[][] times, int n, int k) {

        List<List<Pair>> adj = new ArrayList<>();

        for (int i=0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] t : times) {
            adj.get(t[0]).add(new Pair(t[2], t[1]));
        }
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.first - b.first);
        pq.offer(new Pair(0, k));

        while (!pq.isEmpty()) {

            Pair p = pq.poll();
            int du = p.first;
            int u = p.second;

            for (Pair it : adj.get(u)) {
                int ew = it.first;
                int v = it.second;

                if(ew + du < dist[v]) {
                    dist[v] = ew + du;
                    pq.offer(new Pair(dist[v], v));
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++) {
            res = Math.max(res, dist[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int S) {

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);

        dist[S] = 0;

        // O(V x E)
        for(int i=1; i<=V; i++) {
            // perform n-1 relax to reach the shortest path,
            // but nth relax for check if it is a negative cycle

            for(ArrayList<Integer> ed : edges) {

                int u = ed.get(0);
                int v = ed.get(1);
                int wt = ed.get(2);

                if (dist[u] != (int)1e8 && dist[u] + wt < dist[v]) {

                    if(i == V) return new int[] {-1};  // nth relax to check for negative cycle

                    dist[v] = dist[u] + wt;
                }
            }
        }
        return dist;
    }

    public void shortest_distance(int[][] mat) {

        int n = mat.length;

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {

                if(mat[i][j] == -1) mat[i][j] = (int)1e9;
                if(i == j) mat[i][j] = 0;
            }
        }
        // O(n^3)
        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    mat[i][j] =  Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
        // to detect negative cycle
        for (int i = 0; i < n; i++) {
            if(mat[i][i] < 0) {
                System.out.println("The given matrix contains negative cycle");
                return;
            }
        }
        for (int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j] == (int)1e9) mat[i][j] = -1;
            }
        }
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] dist = new int[n][n];
        int smallest = n; int city = 0;

        for (int[] d : dist) Arrays.fill(d, 10001);

        for (int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];
            dist[u][v] = dist[v][u] = wt;
        }

        for (int i=0; i<n; i++) dist[i][i] = 0;

        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    dist[i][j] =  Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i=0; i<n; i++) {
            int count = 0;

            for(int j=0; j<3; j++) {
                if(dist[i][j] <= distanceThreshold) count++;
            }
            if (count <= smallest) {
                smallest = count;
                city = i;
            }
        }
        return city;
    }
}
