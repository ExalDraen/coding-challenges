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
        System.out.printf("Input: %s\nOutput: %s\n\n", Arrays.toString(nums), threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
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
