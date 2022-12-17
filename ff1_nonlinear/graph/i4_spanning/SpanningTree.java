package com.ff1_nonlinear.graph.i4_spanning;
import com.ff1_nonlinear.graph.i3_ShortestPath.Pair;

import java.util.*;

public class SpanningTree {

    public static void main(String[] args) {

    }

    // Prims Algo
    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        // space -> O(v) + O(E) -> ~O(E)  -> in pq
        boolean[] vis = new boolean[V];

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.first - b.first);
        pq.offer(new Pair(0, 0));
        int sum = 0;
        // E    -> time -> E logE + E logE ~ E logE
        while (!pq.isEmpty()) {

            // pop -> logE
            Pair pair = pq.poll();
            int w = pair.first;
            int u = pair.second;

            if(vis[u]) continue;

            vis[u] = true;

            sum += w;

            // E logE, in worst case 1 node is connected to all others node
            for (ArrayList<Integer> li : adj.get(u)) {

                int v = li.get(0);
                int edW = li.get(1);
                // logE
                pq.offer(new Pair(edW, v));
            }
        }
        return sum;
    }
}
