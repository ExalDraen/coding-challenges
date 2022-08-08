package org.alnx.leetcode.medium;

import java.util.*;
import java.util.stream.Stream;

/**
 * <a href="https://leetcode.com/problems/3sum/">3 Sum</a>
 */
public class ThreeSum {

    public static void main(String[] args) {
        printTc(new int[]{-1, 0, 1, 2, -1, -4});
    }

    private static void printTc(int[] nums) {
        System.out.printf("[Set] Input: %s\n      Output: %s\n", Arrays.toString(nums), threeSumHashSet(nums));
        System.out.printf("[2P ] Input: %s\n      Output: %s\n\n", Arrays.toString(nums), threeSumTwoPointers(nums));
    }

    public static List<List<Integer>> threeSumTwoPointers(int[] nums) {
        // sort array

        // two pointers: one at head, one at tail
        // look at all elements between pointers: if they sum to 0 -> success
        // otherwise if sum < target, increment lower pointer, if sum > target decrement higher
        // this way we tighten the bound until we either find a match or cross or >
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;  // can't get anymore zero sums, everything else is >0
            }
            if (i == 0 || nums[i - 1] != nums[i]) {  // skip duplicates
                twoSumTwoPointers(nums, i, ret);
            }
        }
        return ret;
    }

    private static void twoSumTwoPointers(int[] nums, int i, List<List<Integer>> res) {
        int complement = nums[i];
        int start = i + 1;
        int end = nums.length - 1;
        while (start < end) {
            int sum = nums[start] + nums[end] + complement;
            if (sum == 0) {
                // found a match
                res.add(List.of(nums[start], nums[end], complement));
                start++;
                end--;
                // if there's dupes following this item, skip them
                while (start < end && nums[start] == nums[start - 1]) {
                    start++;
                }
            } else if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //return threeSumHashSet(nums);
        return threeSumTwoPointers(nums);
    }

    private static List<List<Integer>> threeSumHashSet(int[] nums) {
        // all triplets (i,j,k) which sum to 0

        // naive solution: n^3, for each index -> look at all following, check if sum to 0

        // better solution: n^2: go through and find value at each index, store in hash map
        // then find all index pairs (j,k) and check if -(j+k) is in the hash map
        // if yes add to solution

        var soln = new HashSet<Triplet>();
        var lookup = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            lookup.put(nums[i], i);
        }
        for (int j = 0; j < nums.length; j++) {
            for (int k = j + 1; k < nums.length; k++) {
                int sum = -nums[j] - nums[k];
                Integer idx = lookup.get(sum);
                if (idx != null && idx != j && idx != k) {
                    soln.add(new Triplet(nums[j], nums[k], sum));
                }
            }
        }
        return soln
                .stream()
                .map(t -> List.of(t.x, t.y, t.z))
                .toList();
    }

    static final class Triplet {
        private final int x;
        private final int y;
        private final int z;

        Triplet(int x, int y, int z) {
            // always ascending order
            int[] tmp = new int[]{x, y, z};
            Arrays.sort(tmp);
            this.x = tmp[0];
            this.y = tmp[1];
            this.z = tmp[2];
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Triplet) obj;
            return this.x == that.x &&
                    this.y == that.y &&
                    this.z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "Triplet[" +
                    "x=" + x + ", " +
                    "y=" + y + ", " +
                    "z=" + z + ']';
        }
    }
}
