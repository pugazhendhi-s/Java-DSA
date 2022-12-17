package com.ff4_greedy.mediumHard;

import java.util.Arrays;
import java.util.Comparator;

public class JobSequence {

    public static void main(String[] args) {

        Job[] jobs = new Job[4];
        jobs[0] = new Job(1, 4, 20);
        jobs[1] = new Job(2, 1, 10);
        jobs[2] = new Job(3, 2, 40);
        jobs[3] = new Job(4, 1, 30);

        int[] res = JobScheduling(jobs, jobs.length);

        System.out.println(Arrays.toString(res));
    }
    static class Job {

        int id;
        int deadline;
        int profit;

        public Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static int[] JobScheduling(Job[] jobs, int n) {

        Arrays.sort(jobs, Comparator.comparingInt(a -> -a.profit));

        int maxDead = -1;
        for (int i = 0; i < n; i++) {
            maxDead = Math.max(maxDead, jobs[i].deadline);
        }

        boolean[] visited = new boolean[maxDead+1];
        int jobDone = 0, maxProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = jobs[i].deadline; j > 0; j--) {
                if (! visited[j]) {
                    visited[j] = true;
                    jobDone++;
                    maxProfit += jobs[i].profit;
                    break;
                }
            }
        }
        return new int[] {jobDone, maxProfit};
    }
}
