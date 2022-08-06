package org.alnx.leetcode.medium;

import java.util.*;

public class CloneGraph {

    public static void main(String[] args) {
        var n1 = new Node(1);
        var n2 = new Node(2);
        var n3 = new Node(3);
        var n4 = new Node(4);
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        var newGraph = cloneGraph(n1);
        System.out.printf("Input:%s\nOutput:%s\n", n1, newGraph);
    }

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // need to return a clone of a node connected to the same graph

        // strategy: bfs + create a copy at each stage
        var q = new ArrayDeque<Node>();
        var cloneLookup = new HashMap<Integer, Node>();
        var newRoot = new Node(node.val);
        cloneLookup.put(newRoot.val, newRoot);

        q.push(node);
        while (!q.isEmpty()) {
            var cur = q.poll();
            var curNew = cloneLookup.get(cur.val);
            for (var n : cur.neighbors) {
                if (!cloneLookup.containsKey(n.val)) {
                    q.add(n);
                    var newNode = new Node(n.val);
                    cloneLookup.put(newNode.val, newNode);
                }
                curNew.neighbors.add(cloneLookup.get(n.val));
            }
        }
        return newRoot;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", neighbors=" + neighbors.stream().map(n -> n.val).toList() +
                    '}';
        }
    }
}

