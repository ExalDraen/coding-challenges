package org.alnx.leetcode.medium;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        var tc0 = new int[]{1, 2, 5};
        var n0 = 11;
        var tc1 = new int[]{5, 1, 2};
        var n1 = 11;
        var tc2 = new int[]{2};
        var n2 = 3;
        var tc3 = new int[]{1};
        var n3 = 0;
        var tc4= new int[]{2, 3, 7};
        var n4 = 24;
        var tc5= new int[]{1, 2147483647};
        var n5 = 2;

        var tc6 = new int[]{186,419,83,408};
        var n6 = 6249;

        printTest(tc0, n0);
        printTest(tc1, n1);
        printTest(tc2, n2);
        printTest(tc3, n3);
        printTest(tc4, n4);
        printTest(tc5, n5);
        printTest(tc6, n6);
    }

    private static void printTest(int[] coins, int amount) {
        System.out.printf("coins = %s, amount = %d\nOutput = %d\n\n",
                Arrays.toString(coins), amount, coinChange(coins, amount)
        );
    }

    public static int coinChange(int[] coins, int amount) {
        // greedy algorithm?

        // start with largest
        // ...
        // factorization method
        // start with largest
        // rem = remainder(amount, current);
        // div = amount % current;
        // if rem = 0 -> return div
        // if rem !=0 -> coinChange(other coins, remainder);
        // if gcd(amount, largest) != 1 -> return largest % amount
        // otherwise

        if (amount == 0) { return 0;}
        return bottomUp(coins, amount);
    }



    private static int bottomUp(int[] coins, int amount) {
        // key insight:
        // calculate change for same set of coins from 0...amount
        // then change(i+1) = min[all coins c](change(i - c) + 1);
        int[] memo = new int[amount +1];  // stores optimal solution for values up to amount
        Arrays.fill(memo, amount + 1);  // initial value is max, makes the min() below easier
        memo[0] = 0; // making 0 takes 0 coins

        for (int i = 1;i<=amount;i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    memo[i] = Math.min(memo[i], memo[i-coin] + 1);
                }
            }
        }
        return memo[amount] > amount ? - 1: memo[amount];
    }
    /**
     * broken
     */
    private static int rChange(int current, int[] coins, int amount) {
        if (current < 0) {
            return - 1;
        }
        var cur = coins[current];
        if (cur > amount) {
            return rChange(current - 1, coins, amount);
        }
        var div = amount / cur;
        var rem = amount % cur;
        if (rem == 0) {
            return div;
        }
        for (int j = current - 1;j>=0;j--) {
            var res = rChange(j, coins, rem);
            if (res != -1) {
                return div + res;
            }
        }
        return rChange(current - 1, coins, amount);
    }
}
