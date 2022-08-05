package org.alnx.leetcode.easy;

/**
 * <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">max depth</a>
 */
public class BSTMaxDepth {

    public static void main(String[] args) {
        var tc1 = new TreeNode(1, null, new TreeNode(2));
        System.out.printf("Input: %s, Output: %s\n", tc1, maxDepth(tc1));
    }


    //TODO: using static here is a bug if it runs more than once.
    static int maxDepth = 0;
    public static int maxDepth(TreeNode root) {
        // need to traverse whole tree
        //  n
        postorder(root, 1);
        return maxDepth;
    }

    private static void postorder(TreeNode root, int depth) {
        if (root != null) {
            postorder(root.left, depth+1);
            postorder(root.right, depth+1);
            maxDepth = Math.max(depth, maxDepth);
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