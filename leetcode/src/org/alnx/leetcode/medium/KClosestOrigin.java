package org.alnx.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/k-closest-points-to-origin/">K closest points</a>
 */
public class KClosestOrigin {

    public static void main(String[] args) {
        var tc1 = new int[][]{
                new int[]{3, 3},
                new int[]{5, -1},
                new int[]{-2, 4}
        };
        var tc2 = new int[][]{
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{1, 1}
        };
        printTest(tc1, 2);
        printTest(tc2, 2);
    }

    private static void printTest(int[][] points, int k) {
        System.out.printf("Input :%s\nClosest %d: %s\n",
                Arrays.deepToString(points),
                k,
                Arrays.deepToString(kClosest(points, k)));
    }

    public static int[][] kClosest(int[][] points, int k) {
        var numPoints = points.length;
        // add item to min heap
        // pop off k items
        var heap = new PriorityQueue<Coord>(Comparator.naturalOrder());
        for (int[] point : points) {
            var c = new Coord(point[0], point[1]);
            heap.add(c);
        }

        var res = new int[k][2];
        for (int j = 0; j < k; j++) {
            var cur = heap.poll();
            res[j][0] = cur.x;
            res[j][1] = cur.y;
        }
        return res;
    }

    static class Coord implements Comparable<Coord> {
        final int x, y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coord other) {
            var otherDist = other.x * other.x + other.y * other.y;
            var myDist = x * x + y * y;
            return Integer.compare(myDist, otherDist);
        }
    }
}
