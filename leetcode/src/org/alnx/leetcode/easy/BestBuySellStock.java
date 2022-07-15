package org.alnx.leetcode.easy;

public class BestBuySellStock {
    class Solution {
        public int maxProfit(int[] prices) {
            // choose two days
            // one to buy
            // one to sell
            // compute profit

            // if no profit achievable, return 0

            // find biggest difference between two subsequent days
            // where day1 is before day2

            // naive approach which I think is N^2

            // iterate through twice nested
            // for each entry (buy date)
            // look through rest of array, and find best sell date

//         int buyIndex = -1;
//         int sellIndex = -1;
//         int maxProfit = 0;

//         for(int i=0;i<prices.length;i++){
//             int today = prices[i];
//             int curMax = 0;
//             for(int j =i;j<prices.length;j++) {
//                 if(prices[j] - today > curMax) {
//                     curMax = prices[j] - today;
//                 }
//             }
//             if (curMax > maxProfit) {
//                 maxProfit = curMax;
//             }
//         }

            // as we iterate
            // keep track of lowest price we've seen up to now
            // check if today's profit would be > max profit
            int maxProfit = 0;
            int lowestToDate = 10001;
            for(int i=0;i<prices.length;i++) {
                int today = prices[i];

                if (today - lowestToDate > maxProfit) {
                    maxProfit = today - lowestToDate;
                }

                if (today < lowestToDate) {
                    lowestToDate = today;
                }
            }


            return maxProfit;
        }
    }
}
