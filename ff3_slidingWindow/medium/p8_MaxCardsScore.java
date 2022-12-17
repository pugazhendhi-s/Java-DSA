package com.ff3_slidingWindow.medium;

public class p8_MaxCardsScore {

    public static void main(String[] args) {

        int[] cardPoints = {9,3,2,55,66,1,2,8};
        int k = 3;
        int maxScore = maxScore(cardPoints, k);
        System.out.println(maxScore);
    }

    public static int maxScore(int[] cp, int k) {

        int n = cp.length;
        int score = 0;
        for (int i=0; i<k; i++) {
            score += cp[i];
        }
        int curScore = score;
        int j = n-1;
        for (int i=k-1; i>=0; i--) {
            curScore = curScore - cp[i] + cp[j--];
            score = Math.max(curScore, score);
        }
        return score;
    }
}
