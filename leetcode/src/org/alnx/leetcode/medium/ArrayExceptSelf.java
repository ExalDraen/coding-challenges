package org.alnx.leetcode.medium;

public class ArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // product of all num except self

        // in constant number of passes

        // naive solution: for each entry, look at all others and multiply
        // n* (n-1) -> n^2

        // multiply as we do forward pass and set result to that
        // do backward and do the same


        // [1,2,3,4]
        // [1,1*1,1*1*2,1*1*2*3] // forward pass
        // [1,1,2,6]
        // [1*2*3*4,    1*3*4,        2*4       ,6] // backward pass

        int[] res = new int[nums.length];
        int product = 1;

        // initialize with 1s
        // Arrays.fill(res, 1);

        // forward pass
        for (int i=0;i<nums.length;i++) {
            res[i] = product;
            product = product * nums[i];
        }

        // backward pass
        product = 1;
        for (int j=nums.length-1;j>=0;j--) {
            res[j] = res[j] * product;
            product = product * nums[j];
        }
        return res;
    }
}
