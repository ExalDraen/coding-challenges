package org.alnx.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberOfIslands {

    public static void main(String[] args) {
        var tc1 = new char[][]{
                "11110".toCharArray(),
                "11010".toCharArray(),
                "11000".toCharArray(),
                "00000".toCharArray()
        };
        var tc2 = new char[][]{
                "11000".toCharArray(),
                "11000".toCharArray(),
                "00100".toCharArray(),
                "00011".toCharArray()
        };
        printTc(tc1);
        printTc(tc2);
    }

    private static void printTc(char[][] grid) {
        System.out.printf("Input:\n%s\nNum. Islands = %d\n", Arrays.deepToString(grid), numIslands(grid));
    }


    public static int numIslands(char[][] grid) {
        //m rows
        var m = grid.length;
        var n = grid[0].length;
        int islands = 0;
        var visited = new HashSet<Coord>();

        // bfs whenever find a 1
        // find all connected 1s
        // mark as visited
        // continue

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    var c = new Coord(i, j);
                    if (!visited.contains(c)) {
                        islands++;
                        dfs(c, grid, visited);
                    }
                }
            }
        }
        return islands;
    }

    private static void dfs(Coord start, char[][] grid, Set<Coord> visited) {
        visited.add(start);
        for (var c : neighbours(grid, start)) {
            if (!visited.contains(c)) {
                dfs(c, grid, visited);
            }
        }
    }

    private static Set<Coord> neighbours(char[][] grid, Coord start) {
        var ret = new HashSet<Coord>();
        // below
        if (start.row < grid.length - 1 && grid[start.row + 1][start.col] == '1') {
            ret.add(new Coord(start.row + 1, start.col));
        }
        // above
        if (start.row > 0 && grid[start.row - 1][start.col] == '1') {
            ret.add(new Coord(start.row - 1, start.col));
        }
        // to the right
        if (start.col < grid[0].length - 1 && grid[start.row][start.col + 1] == '1') {
            ret.add(new Coord(start.row, start.col + 1));
        }
        // to the left
        if (start.col > 0 && grid[start.row][start.col - 1] == '1') {
            ret.add(new Coord(start.row, start.col - 1));
        }
        return ret;
    }

    static class Coord {
        final int row;
        final int col;

        Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (row != coord.row) return false;
            return col == coord.col;
        }
    }
}
