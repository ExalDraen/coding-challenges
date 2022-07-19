package org.alnx.leetcode.easy;

import java.util.*;

/**
 * https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        var graph = imageToGraph(image);

        var q = new ArrayDeque<Vertex>();
        var start = new Vertex(sr, sc, color);
        q.add(start);
        while(!q.isEmpty()) {
            var current = q.remove();
            image[current.r][current.c] = color;
            q.addAll(graph.get(current));
        }
        // find all pixels connected to sr,sc pixel 4-directionally
        // change all of those pixels to color

        // use a graph to represent pixels and their 4-directional connectivity

        // edges -> any 4 side where dst is not 0
        // vertices -> pixels

        // bfs/dfs to explore whole tree
        return image;
    }

    private Map<Vertex, List<Vertex>> imageToGraph(int[][]image) {
        var m = image.length;
        if (m == 0) {
            return Collections.emptyMap();
        }
        var n = image[0].length;
        if (n == 0) {
            return Collections.emptyMap();
        }

        var graph = new HashMap<Vertex, List<Vertex>>();

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if (image[i][j] != 0) {
                    graph.put(new Vertex(i,j, image[i][j]), adjacentVertices(i,j,image));
                }
            }
        }
        return graph;
    }

    private static List<Vertex> adjacentVertices(int i, int j, int[][] image) {
        var res = new ArrayList<Vertex>();
        var m = image.length;
        var n = image[0].length;

        // left
        if (i > 0 && image[i-1][j] != 0) {
            res.add(new Vertex(i-1,j,image[i-1][j]));
        }
        // right
        if (i < m -1 && image[i+1][j] != 0) {
            res.add(new Vertex(i+1,j,image[i+1][j]));
        }
        // above
        if (j > 0 && image[i][j-1] != 0) {
            res.add(new Vertex(i,j-1,image[i][j-1]));
        }
        // below
        if (j < n-1 && image[i][j+1] != 0) {
            res.add(new Vertex(i,j+1,image[i][j+1]));
        }
        return res;
    }

    static class Vertex {
        public int r;
        public int c;
        public int col;
        Vertex(int r, int c, int col) {
            this.r = r;
            this.c = c;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return r == vertex.r && c == vertex.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
