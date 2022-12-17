package com.ff4_greedy.easy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FractionalKnapsack {

    public static void main(String[] args) {

        Item[] items = new Item[3];
        items[0] =  new Item(30, 120);
        items[1] =  new Item(10, 60);
        items[2] =  new Item(20, 100);

        int maxWeight = 50;

        double maxValues = fractionalKnapsack(maxWeight, items);
        System.out.println(maxValues);
    }
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static double fractionalKnapsack(int W, Item[] items) {
        /*
         formula for sorting according to fraction ---

         b.value/b.weight= a.value/a.weight ---> b.value * b.weight = a.value * b.weight

         b.value first because we need to sort in descending (b - a) this is return there
         it's done bcz in divide we may get double type value so in that case
         we need to typecast so to avoid typecasting we are using math formula
         */
        Arrays.sort(items, (a, b) -> ((b.value * a.weight) - (a.value * b.weight)));
        double maxValue = 0.0;

        for (Item it : items) {
            double wt = it.weight;
            double val = it.value;
            if (wt <= W) {
                maxValue += val;
                W -= wt;
            }
            else {
                maxValue += (val / wt) * W;  // perWeight * remainingWeight
                break;
            }
        }
        return maxValue;
    }

    // using priority queue

    public static double fractional(int W, Item[] items) {

        // PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> compare(a, b));
        PriorityQueue<Item> pq = new PriorityQueue<>(FractionalKnapsack::compare);
        for (Item it : items) {
            pq.offer(it);
        }
        double maxValue = 0;

        while (! pq.isEmpty()) {
            Item it = pq.poll();
            double wt = it.weight;
            double val = it.value;

            if (wt <= W) {
                maxValue += val;
                W -= wt;
            }
            else {
                maxValue += (val / wt) * W;  // perWeight * remainingWeight
                break;
            }
        }
        return maxValue;
    }

    public static int compare(Item a, Item b) { // we can directly perform inline lamda expressions
        return a.weight * b.value -  b.weight * a.value;
    }
}
