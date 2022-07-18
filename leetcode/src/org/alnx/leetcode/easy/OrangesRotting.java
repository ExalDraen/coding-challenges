package org.alnx.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OrangesRotting {

    public static void main(String[] args) {
        var grid = new int[][]{
            new int[]{2,1,1},
            new int[]{1,1,0},
            new int[]{0,1,1}
        };
        System.out.printf("%s\n%s\n", Arrays.deepToString(grid), orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        // any adjacent cell has rotten orange -> rot

        // simple plan: execute `tick` loop until no orange rots further
        // check if number rotted = total number
        // if yes, return number of ticks, else -1

        var m = grid.length;
        // edge case: empty grid
        if (m == 0) {
            return -1;
        }
        var n = grid[0].length;
        // another empty grid edge case
        if (n == 0) {
            return -1;
        }

        int tick = 0;
        boolean anyFresh = false;
        var justRotted = new ArrayList<List<Integer>>();

        while (true) {
            justRotted.clear();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // nothing happens to empty or rotten cells
                    if (grid[i][j] == 1) {
                        if (rotAdjacent(i, j, grid)) {
                            justRotted.add(Stream.of(i, j).toList());
                        } else {
                            anyFresh = true;
                        }
                    }
                }
            }
            if (!justRotted.isEmpty()) {
                tick++;
                // update grid
                for (var r : justRotted) {
                    grid[r.get(0)][r.get(1)] = 2;
                }
                anyFresh = false;
            } else {
                if (anyFresh) {
                    return -1;
                }
                break;
            }
        }
        return tick;
    }

    private static boolean rotAdjacent(int i, int j, int[][] grid) {
        var m = grid.length;
        var n = grid[0].length;

        // left
        if (i > 0) {
            if (grid[i - 1][j] == 2) {
                return true;
            }
        }
        // right
        if (i < (m - 1)) {
            if (grid[i + 1][j] == 2) {
                return true;
            }
        }
        // above
        if (j > 0) {
            if (grid[i][j - 1] == 2) {
                return true;
            }
        }
        // below
        if (j < (n - 1)) {
            if (grid[i][j + 1] == 2) {
                return true;
            }
        }

        return false;
    }
}
