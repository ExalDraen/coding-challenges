package org.alnx.leetcode.easy;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 */
public class InvertBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {return null;}
            return invertNode(root);
            // what does inversion mean?

            // every node still same children, just exactly opposite order
            // in other words, instead of left > right
            // right > left

            // given the root, which we need to return after making changes

            // root
            // switch left/right
            // go left: switch left/right

            // - base case: no children -> return
            // - 2 children: recurse left first
            // - 1 child: recurse
        }

        public TreeNode invertNode(TreeNode root) {
            if (root.left == null && root.right == null) {
                return root;
            }
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            if (root.left != null) {
                root.left = invertNode(root.left);
            }
            if (root.right != null) {
                root.right = invertNode(root.right);
            }
            return root;
        }
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
