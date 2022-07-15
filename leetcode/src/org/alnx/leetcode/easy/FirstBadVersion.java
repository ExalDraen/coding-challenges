package org.alnx.leetcode.easy;

/**
 * https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {
    public static class Solution extends VersionControl {
        public static void main(String[] args) {
            System.out.println(firstBadVersion(5));
        }

        public static int firstBadVersion(int n) {
            // [1, 2 ... n]
            // somewhere i is bad, and everything after it is bad

            // can it recover? no, assume once bad, will stay bad

            // naive approach
            // find any bad entry, and then scan left for first bad entry
            // -- want to minimize calls, so this is not ideal

            // binary search type approach
            // check n/2: if bad, consider subarray of 0->n/2, otherwise n/2->n
            // how do we terminate?
            // we find a subarray [b,g]
            // once size 2, answer is start+1
            // should find answer in O(log n)
            int start = 0;
            int end = n;
            // special case: n=1
            if( n == 1) {
                return 1;
            }
            while(true) {
                int subSize = end-start;
                // terminate once we're down to 2
                if (subSize == 1) {
                    return start+1;
                }
                int halfway = (start + (subSize/2)); // java truncates?
                if (isBadVersion(halfway)) {
                    end = halfway;
                } else {
                    start = halfway;
                }
            }
        }
    }
}

class VersionControl {
    static boolean isBadVersion(int version) {
        return version >= 4;
    }
}