package org.alnx.leetcode.easy;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/01-matrix/">01 Matrix</a>
 */
public class Matrix01 {

    public static void main(String[] args) {
        var tc1 = new int[][]{
                new int[]{0, 0, 0},
                new int[]{0, 1, 0},
                new int[]{0, 0, 0}
        };
        var tc2 = new int[][]{
                new int[]{0, 0, 0},
                new int[]{0, 1, 0},
                new int[]{1, 1, 1}
        };
        printTest(tc1);
        printTest(tc2);
    }

    private static void printTest(int[][] mat) {
        System.out.println("-----------------------");
        System.out.printf("Input: %s\nOutput: %s\n", Arrays.deepToString(mat), Arrays.deepToString(updateMatrix(mat)));
    }

    /**
     * Find distance to nearest 0 for each cell
     */
    public static int[][] updateMatrix(int[][] mat) {
        return bNearest(mat);
    }

    private static int[][] naiveNearest(int[][] mat) {
        var m = mat.length; // nr. of rows
        var n = mat[0].length; //nr. of columns
        var res = new int[m][n];

        // naive solution: bfs from each cell until we find 0
        // improvement: start with the 0s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = nearest(i, j, mat);
            }
        }
        return res;
    }

    private static int[][] bNearest(int[][] mat) {
        var m = mat.length; // nr. of rows
        var n = mat[0].length; //nr. of columns
        var res = new int[m][n];

        var q = new ArrayDeque<Coord>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Coord(i, j));
                    res[i][j] = 0;
                } else {
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!q.isEmpty()) {
            var cur = q.pop();
            for (var c : neighbours(cur, m, n)) {
                if (res[c.row][c.col] > res[cur.row][cur.col] + 1) {
                    res[c.row][c.col] = res[cur.row][cur.col] + 1;
                    q.add(c);
                }
            }
        }
        return res;
    }

    private static int nearest(int row, int col, int[][] mat) {
        var m = mat.length; // nr. of rows
        var n = mat[0].length; //nr. of columns
        if (mat[row][col] == 0) {
            return 0;
        }
        var visited = new HashSet<Coord>();
        var q = new ArrayDeque<Coord>();
        q.add(new Coord(row, col));
        while (!q.isEmpty()) {
            var cur = q.pop();
            visited.add(cur);
            if (mat[cur.row][cur.col] == 0) {
                return Math.abs(row - cur.row) + Math.abs(col - cur.col);
            }
            for (var c : neighbours(cur, m, n)) {
                if (!visited.contains(c)) {
                    q.add(c);
                }
            }
        }
        return -1;  // no 0 found
    }

    private static Set<Coord> neighbours(Coord cur, int numRows, int numCols) {
        var res = new HashSet<Coord>();
        // left
        if (cur.col > 0) {
            res.add(new Coord(cur.row, cur.col - 1));
        }
        // right
        if (cur.col + 1 < numCols) {
            res.add(new Coord(cur.row, cur.col + 1));
        }
        // down
        if (cur.row > 0) {
            res.add(new Coord(cur.row - 1, cur.col));
        }
        // up
        if (cur.row + 1 < numRows) {
            res.add(new Coord(cur.row + 1, cur.col));
        }
        return res;
    }

    static class Coord {
        final int row, col;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (row != coord.row) return false;
            return col == coord.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
}