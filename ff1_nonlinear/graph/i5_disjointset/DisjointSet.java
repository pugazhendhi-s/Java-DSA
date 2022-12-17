package com.ff1_nonlinear.graph.i5_disjointset;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

    private final List<Integer> rank = new ArrayList<>();
    private final List<Integer> parent = new ArrayList<>();
    private final List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {

        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int node) {

        if (node == parent.get(node)) return node;

        int ulp = findParent(parent.get(node));  // ulp -> ultimate parent
        parent.set(node, ulp); // path compression

        return parent.get(node);
    }

    private void unionByRank(int u, int v) {

        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if (ulp_u == ulp_v) return;

        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        }
        else if (rank.get(ulp_u) > rank.get(ulp_v)) {
            parent.set(ulp_v, ulp_u);
        }
        else {
            parent.set(ulp_v, ulp_u);
            int r_u = rank.get(ulp_u);
            rank.set(ulp_u, r_u + 1);
        }
    } // use any one union by size or rank

    public void unionBySize(int u, int v) {

        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if (ulp_u == ulp_v) return;

        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        }
        else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }

    public int getSize(int ulp) {
        return size.get(ulp);
    }

}

