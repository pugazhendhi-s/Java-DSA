package com.ff2_dp.stocks;

public class StockI {

    public static void main(String[] args) {


    }
    public int maxProfit(int[] prices) {

        int buy = (int)1e9;
        int sell = 0;

        for (int stock : prices) {
            buy = Math.min(buy, stock);
            sell = Math.max(sell, stock - buy);
        }
        return sell;
    }


}
