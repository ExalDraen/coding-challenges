package org.alnx.prep;

import java.util.HashSet;

/**
 * https://algodaily.com/challenges/lonely-number
 */
public class SingleLonelyNumber {


    // find single lonely number
    // find the only one that appears once

    // O(n) space solution: use hash set to store as you
    // iterate, and check if key already

    public static int lonelyNumber(int[] numbers) {
        var seen = new HashSet<Integer>();

        for (var n : numbers) {
            if(!seen.contains(n)) {
                return n;
            }
            seen.add(n);
        }
        return 0;
    }

    // bitset of length n
    // elments in array: power of 2
    // [1,2,9,5,5]
    // 1*2^0+2*2^2+...
    // xor(2^i, 1) == 1 => we've seen it already
    // would take n bits to store
    // rather than (size int) * bits
    //

    // mathematical property like evenness
    //

}
