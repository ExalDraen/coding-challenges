package org.alnx.leetcode.medium;

import java.util.Arrays;

public class RotatedBinarySearch {
    public static void main(String[] args) {
        printTc(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        printTc(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
        printTc(new int[]{3,1}, 1);
    }

    private static void printTc(int[] nums, int target) {
        System.out.printf("Nums: %s, Target: %d\nOutput: %d\n\n", Arrays.toString(nums), target, search(nums, target));
    }

    public static int search(int[] nums, int target) {

        // sorted, but possibly rotated, like a circular array
        // simple solution:
        // scan to find lowest element, start a binary search from there
        // find the element O(n)

        // O (log n)
        // [4,5,6,7,0,1,2]
        // [4,5,6] [7,0,1,2] target = 5

        // numbers < first numbers > last -> in the other half
        //
        // < last element
        // > first element  -> in the half
        return revBinSearch(nums, target);
    }

    private static int revBinSearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1;

        while (start <= end) {
            int mid = start + (end - start) /2;
            if (nums[mid] == target) {
                return mid;
            }
            // first half no pivot?
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid -1;
                }
            }
        }
        return -1;
    }

    private static int binSearch(int[] nums, int target, int lo, int hi) {
        if (lo == hi) {
            return -1;
        }
        if (hi - lo == 1) {
            if (nums[lo] == target) {
                return lo;
            } else {
                return -1;
            }
        }
        int mid = (hi - lo) / 2;
        int newlo = lo + mid;
        //int newhi = hi - mid;

        // Contains pivot if first number > last number
        if (nums[lo] > nums[hi - 1]) {
            // in that case if lower half has pivot and value < first value in lower
            // then it must be in the other half
            if (nums[lo] > nums[newlo]) {
                if (target < nums[lo] && target > nums[newlo]) {
                    return binSearch(nums, target, newlo, hi);
                } else {
                    return binSearch(nums, target, lo, newlo);
                }
            } else { // if lower half doesn't have the pivot, then just check if target less than end or not
                if (target < nums[newlo] && target > nums[lo]) {
                    return binSearch(nums, target, lo, newlo);
                } else {
                    return binSearch(nums, target, newlo, hi);
                }
            }
        } else {
            if (target < nums[newlo]) {
                return binSearch(nums, target, lo, newlo);
            } else {
                return binSearch(nums, target, newlo, hi);
            }
        }
    }
}
