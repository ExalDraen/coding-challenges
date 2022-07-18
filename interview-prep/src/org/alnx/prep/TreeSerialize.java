package org.alnx.prep;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

/**
 * untested, likely still has bugs
 *
 * https://algodaily.com/challenges/serialize-and-deserialize-a-binary-tree
 */
public class TreeSerialize {
    // given binary tree
    // want to serialize and deserialize to exact same tree

    // level order traversal defines the tree exactly
    // so sending level order over the wire works
    // need to find the correct way to reconstruct the tree

    // it's general binary tree so should be able to do this from the level order traversal

    // assume we serialize to a string and then send over the wire maybe with http

    // assume class structure of the bst
    static class Node {
        public int data;
        public Node left;
        public Node right;
        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        Node(int data) { this(data,null,null); }
    }

    private static final String SEPARATOR = "j";

    public static String serialize(Node root) {
        // string builder represents serialized output
        StringBuilder out = new StringBuilder();

        // level order traversal
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(root);

        while(!q.isEmpty()) {
            var current = q.pop();
            out.append(current.data);
            out.append(SEPARATOR);
            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
        return out.toString();
    }

    public Node deserialize(String streamedData) {
        // split up string back into data pieces

        String[] bits = streamedData.split(SEPARATOR);
        // should do some error handling to deal deserialization issues
        if (bits.length == 0) {
            return null;
        }

        List<Integer> intBits = Arrays
                .stream(bits)
                .map(Integer::parseInt)
                .toList();
        return rDeserialize(intBits);
    }

    public Node rDeserialize(List<Integer> elements) {
        if (elements.size() == 0) {
            return null;
        }
        int middle = elements.size() % 2;
        var node = new Node(elements.get(middle));
        node.left = rDeserialize(elements.subList(0, middle));
        node.right = rDeserialize(elements.subList(middle, elements.size()));
        return node;
    }

    /// 1
    // 2 3
}
