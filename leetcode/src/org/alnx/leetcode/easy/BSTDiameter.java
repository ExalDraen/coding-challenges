package org.alnx.leetcode.easy;

/**
 *
 * <a href="https://leetcode.com/problems/diameter-of-binary-tree/">BST Diameter</a>
 * <p>The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.</p>
 */
public class BSTDiameter {

    public int diameterOfBinaryTree(TreeNode root) {
        // have to find longest path between nodes

        // should be path between deepest node in each side of the subtree.

        // max (diameter = depth (left) + depth(right)), (diameter(left)) (diameter(right))
        return diameter(root);
    }

    private int diameter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var maxSub = Math.max(diameter(root.left), diameter(root.right));
        return Math.max(maxDepth(root.left) + maxDepth(root.right), maxSub);
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var left_height = maxDepth(root.left);
        var right_height = maxDepth(root.right);
        return Math.max(left_height, right_height) + 1;

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
