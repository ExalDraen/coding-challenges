package org.alnx.prep;

import java.util.Arrays;

/**
 * https://algodaily.com/challenges/jump-game
 */
public class MaxLeaps {
    //
    // [1,3,2,2,1]

    // each int represents max jump
    // don't need to choose max
    // check if we can make it to end
    // in other words, check for *absence* of run of 0s
    //
    // naive implentation
    // for i in array:
    //  check if array[i] -> array[i + array[i]] are all 0
    //  if yes, fail, otherwise continue
    //  O(n^2) in worst case, need to loop n for every entry

    // better: keep track of furthest index we can jump as we go
    // i.e. for [1,3,2,2,1]
    //           1,4,4,5,6  -> success
    // if furthest index is ever <= current index, fail
    public static void main(String[] args) {
        int[] tc1 = new int[]{1,3,2,2,1};
        int[] tc2 = new int[]{0,3,2,2,1};
        int[] tc3 = new int[]{2,1,0,1,4};
        int[] tc4 = new int[]{};
        int[] tc5 = new int[]{5,1,0,0,0,1,4};
        testJumps(tc1);
        testJumps(tc2);
        testJumps(tc3);
        testJumps(tc4);
        testJumps(tc5);
    }

    private static boolean canJump(int[] jumps) {
        int maxReach = 0;
        for(int i = 0;i<jumps.length;i++) {
            int curReach = i + jumps[i];
            if (curReach > maxReach) {
                maxReach = curReach;
            }
            // if our reach does not extend beyond current
            // index -> fail
            if (maxReach <= i) {
                return false;
            }
        }
        // we have gone through whole array and reach did extend beyond end of array -> success
        return true;
    }

    private static void testJumps(int[] jumps) {
        System.out.printf("%s: %s\n", Arrays.toString(jumps), canJump(jumps));
    }
}
