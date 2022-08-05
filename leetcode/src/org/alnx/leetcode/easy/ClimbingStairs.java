package org.alnx.leetcode.easy;

/**
 * <a href="https://leetcode.com/problems/climbing-stairs/">Climbing Stairs</a>
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        printTc(1);
        printTc(2);
        printTc(3);
        printTc(4);
        printTc(5);
        printTc(45);
    }

    private static void printTc(int n) {
        System.out.printf("Input: %d, steps: %d\n", n, climbStairs(n));
    }


    public static int climbStairs(int n) {


        // n=45 -> steps

        // f(n) -> f(n+1)?

        // f(2) (1,1)       (2)
        // f(3) (1,1,1)     (1,2)     (2,1      (         (               )
        // f(4) (1,1,1,1)   (1,1,2)   (1,2,1    (2,1,1    (2,2            )
        // f(5) (1,1,1,1,1) (1,1,1,2) (1,1,2,1) (1,2,1,1) (2,1,1,1) (1,2,2) (2,1,2) (2,2,1)

        // f(n+1) = f(n) + 1 ?

        return iClimb(n);
    }

    private static int rClimb(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return rClimb(n-1)+ rClimb(n-2);
    }

    private static int iClimb(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        var res = new int[n+1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        for (int i=3;i<=n;i++) {
            res[i] = res[i-1] + res[i-2];
        }
        return res[n];
    }
}
