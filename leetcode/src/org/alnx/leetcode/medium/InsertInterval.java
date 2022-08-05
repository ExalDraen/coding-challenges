package org.alnx.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/insert-interval/">Insert Interval</a>
 */
public class InsertInterval {


    public static void main(String[] args) {
        var tc1 = new int[][]{
                new int[]{1, 3},
                new int[]{6, 9}
        };
        printTest(tc1, new int[]{2, 5});

        var tc2 = new int[][]{
                new int[]{1, 2},
                new int[]{3, 5},
                new int[]{6, 7},
                new int[]{8, 10},
                new int[]{12, 16}
        };
        printTest(tc2, new int[]{4, 8});
    }
    // step 1, find place for insertion
    // step 2: merge adjacent if necessary
    // scenarios:
    //  a) contained in left -> do nothing and return
    //  b) overlaps left partially -> set left end to new interval end, merge rigth
    //  c) does not overlap -> merge right
    // merge?
    //  while end > start:
    //

    private static void printTest(int[][] points, int[] newInterval) {
        System.out.println("---------------------------------------");
        System.out.printf("Input :%s, new Interval %s.\nResult: %s\n",
                Arrays.deepToString(points),
                Arrays.toString(newInterval),
                Arrays.deepToString(insert(points, newInterval)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        return greedyInsert(intervals, newInterval);
        //return complicatedInsert(intervals, newInterval);
    }

    private static int[][] greedyInsert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        var out = new ArrayList<Integer[]>();
        int i;
        for (i = 0; i < n ; i++) {
            if (intervals[i][0] < newInterval[0]) {
                out.add(new Integer[]{intervals[i][0], intervals[i][1]});
            } else {
                break;
            }
        }
        addMerge(out, newInterval);
        for(int j = i;j<n;j++) {
            addMerge(out, intervals[j]);
        }
        var res = new int[out.size()][2];
        for (int j = 0; j < res.length; j++) {
            res[j][0] = out.get(j)[0];
            res[j][1] = out.get(j)[1];
        }
        return res;
    }

    private static void addMerge(List<Integer[]> list, int[] toAdd) {
        if (list.size() == 0) {
            list.add(new Integer[]{toAdd[0], toAdd[1]});
            return;
        }
        var last = list.get(list.size() - 1);
        if (last[1] >= toAdd[0]) {
            // merge
            list.remove(list.size() - 1);
            list.add(new Integer[]{last[0], Math.max(last[1], toAdd[1])});
        } else {
            list.add(new Integer[]{toAdd[0], toAdd[1]});
        }
    }

    private static int[][] complicatedInsert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        // special case: off the back
        if (newInterval[0] > intervals[intervals.length - 1][0]) {
            var res = new int[intervals.length + 1][2];
            System.arraycopy(intervals, 0, res, 0, intervals.length);
            res[intervals.length][0] = newInterval[0];
            res[intervals.length][1] = newInterval[1];
            return res;
        }
        for (int i = 0; i < n - 1; i++) {
            if (newInterval[0] < intervals[i + 1][0]) {
                if (newInterval[1] < intervals[i][1]) {
                    // fully contained in left , nothing to do
                    return intervals;
                } else {
                    // keep looking right until we find an start > newINterval end or we exhaust list
                    // merge all of these together
                    int j;
                    for (j = i; j < n - 1; j++) {
                        if (newInterval[1] < intervals[j][0]) {
                            break;
                        }
                    }
                    // now we want
                    // .... ... i [new] ...merge.... j ....
                    var res = new int[intervals.length + 1 + i - j][2];
                    System.arraycopy(intervals, 0, res, 0, i);
                    res[i][0] = newInterval[0];
                    res[i][1] = Math.max(newInterval[1], intervals[j][1]);
                    System.arraycopy(intervals, j, res, i + 1, intervals.length - j);
                    return res;
                }
            }
        }
        return intervals;
    }
}
