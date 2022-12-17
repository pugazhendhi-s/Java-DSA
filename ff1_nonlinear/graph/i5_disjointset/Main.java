package com.ff1_nonlinear.graph.i5_disjointset;

public class Main {

    public static void main(String[] args) {

        DisjointSet djs1 = new DisjointSet(7);
        djs1.unionBySize(1, 2);
        djs1.unionBySize(2, 3);
        djs1.unionBySize(4, 5);
        djs1.unionBySize(6, 7);
        djs1.unionBySize(5, 6);

        if (djs1.findParent(1) == djs1.findParent(7))
            System.out.println("Connected dj");
        else System.out.println("Disconnected dj");

        djs1.unionBySize(3, 7);
        if (djs1.findParent(3) == djs1.findParent(7))
            System.out.println("Connected dj");
        else System.out.println("Disconnected dj");
    }
}
