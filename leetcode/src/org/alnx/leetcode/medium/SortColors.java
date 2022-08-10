package org.alnx.leetcode.medium;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortColors {
    public static void main(String[] args) {
        printTc(new int[]{2,0,1,1,0});
        printTc(new int[]{2,0,1});
    }

    private static void printTc(int[] nums) {
        System.out.printf("Input:%s\n", Arrays.toString(nums));
        sortColors(nums);
        System.out.printf("Output:%s\n\n", Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        // red: 1
        // white: 1
        // blue: 2
        // red,white,blue desired order

        // one algorithm (not sorting in place) would be to count number
        // of each type and then reconstruct algorithm
        // not sure that's allowed?

        // assuming that's *not* allowed, options are
        // selection sort: n^2
        // insertion sort: n^2 worst, n best
        //selectionSort(nums);
        //insertSort(nums);
        quicksort(nums);
    }

    private static void insertSort(int[] nums) {
        int front = 1; // index of first element of unsorted portion
        // ok to start at 1 because 1-lenght array is already sorted
        for (int i=1;i<nums.length;i++) {
            front = i;
            // sort subarray 0..front
            while(front > 0 && nums[front] < nums[front-1]) {
                swap(nums, front, front-1);
                front--;
            }
        }
    }

    private static void selectionSort(int[] nums) {
        int min = 3; // max data is 2
        int minIdx = 0;
        for (int i=0;i<nums.length;i++) {
            minIdx = i;
            min = nums[i];
            for (int j=i;j<nums.length;j++) {
                if (nums[j] < min) {
                    minIdx = j;
                    min = nums[j];
                }
            }
            swap(nums, i, minIdx);
        }
    }
    private static void quicksort(int[] nums) {
        quicksort(nums, 0, nums.length);
    }
    private static void quicksort(int[] nums, int start, int end) {
        // Part 0: base case
        if (end -  start < 1) {
            return; // base case; 1 length is sorted
        }
        // Part 1: Pivot selection
        int pivotIdx = choosePivot(start, end);
        int pivotVal = nums[pivotIdx];
        swap(nums, start, pivotIdx); // move pivot out of the way (to the start)
        // Part 2: partition so that everything > pivotVal is to the right
        // and everything < pivotVal is to the left
        int i = start + 1;
        int j = end - 1;
        while (i <= j) {
            // advance i until we find value > pivot
            while (i<=j && nums[i] <= pivotVal) {
                i++;
            }
            // retreat j until we find value < pivot
            while (i<=j && nums[j] >= pivotVal) {
                j--;
            }
            // found a value to swap (unless we've crossed)
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        // Part 3: i/j have crossed
        // move pivot back into its proper place in the middle of the two halves
        swap(nums, start, j);
        // recurse each half
        quicksort(nums, start, j);
        quicksort(nums, j+1, end);

    }

    private static int choosePivot(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp;
        tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
