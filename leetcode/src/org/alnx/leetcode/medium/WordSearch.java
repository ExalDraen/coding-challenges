package org.alnx.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordSearch {

    public static void main(String[] args) {
        char[][] tc0 = {
                "ABCE".toCharArray(),
                "SFCS".toCharArray(),
                "ADEE".toCharArray()
        };
        String tar0 = "ABCCED";
        String tar1 = "SEE";
        String tar15 = "ABCB";
        char[][] tc2 = {
                "CAA".toCharArray(),
                "AAA".toCharArray(),
                "BCD".toCharArray()
        };
        String tar2 = "AAB";
        printTc(tc0, tar0);
        printTc(tc0, tar1);
        printTc(tc0, tar15);
        printTc(tc2, tar2);
    }

    private static void printTc(char[][] board, String word) {
        System.out.printf("Input: %s, Goal word: %s\nOutput: %s\n\n",
                Arrays.deepToString(board), word, exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        // straightforward solution: dfs from each cell, with backtracking
        var m = board.length;
        var n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int startrow, int startcol, String word) {
        var visited = new HashSet<Coord>();
        return rDfs(board, new Coord(startrow, startcol), visited, 0, word);
    }

    private static boolean rDfs(char[][] board, Coord c, Set<Coord> visited, int curIdx, String word) {
        if (word.charAt(curIdx) != board[c.row][c.col]) {
            return false;
        }
        if (curIdx == word.length() - 1) {
            return true;
        }
        visited.add(c);
        for (var n : neighbours(c, board)) {
            if (!visited.contains(n)) {
                boolean res = rDfs(board, n, visited, curIdx + 1, word);
                if (res) {
                    visited.remove(c);
                    return true;
                }
            }
        }
        visited.remove(c);
        return false;
    }

    private boolean bfs(char[][] board, int startrow, int startcol, String word) {
        var q = new ArrayDeque<Coord>();
        var visited = new HashSet<Coord>();
        int charIdx = 0;

        q.add(new Coord(startrow, startcol));
        while (!q.isEmpty()) {
            var cur = q.pop();
            visited.add(cur);
            if (board[cur.row][cur.col] != word.charAt(charIdx)) {
                continue;
            }
            if (charIdx == word.length() - 1) {
                return true;
            }
            charIdx++;
            // todo: look ahead, don't enqueue neighbours that don't match
            for (var n : neighbours(cur, board)) {
                if (!visited.contains(n) && word.charAt(charIdx) == board[n.row][n.col]) {
                    q.add(n);
                }
            }
        }
        return false;
    }

    private static Set<Coord> neighbours(Coord cur, char[][] board) {
        var m = board.length;
        var n = board[0].length;
        var res = new HashSet<Coord>();

        // above
        if (cur.row < m - 1) {
            res.add(new Coord(cur.row + 1, cur.col));
        }
        // below
        if (cur.row > 0) {
            res.add(new Coord(cur.row - 1, cur.col));
        }
        // right
        if (cur.col < n - 1) {
            res.add(new Coord(cur.row, cur.col + 1));
        }
        // left
        if (cur.col > 0) {
            res.add(new Coord(cur.row, cur.col - 1));
        }
        return res;

    }

    record Coord(int row, int col) {

    }
}
