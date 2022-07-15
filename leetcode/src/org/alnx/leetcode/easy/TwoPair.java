package org.alnx.leetcode.easy;

import java.util.HashMap;

public class TwoPair {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // entire array, find pair that sums to target

            // two, for every element, we can compute what the desired partner would be

            // using the example
            // [2,7,11,15], target = 9
            // [7,2,-2,-6]
            // only one solution, so the first we solution we find, we're done

            // as we go through, keep a record of desired partner
            // if we every find desired partner => we're done

            // need to record the presence *and* index of the desired partner

            // use a hashmap mapping: desiredPartnerValue: index of desiring
            var seen = new HashMap<Integer, Integer>();

            for(int i=0;i<nums.length;i++) {
                if (seen.containsKey(nums[i])) {
                    return new int[]{seen.get(nums[i]), i};
                }
                int desiredPartner = target - nums[i];
                seen.put(desiredPartner, i);
            }
            return new int[]{0,0};
        }
    }
}
