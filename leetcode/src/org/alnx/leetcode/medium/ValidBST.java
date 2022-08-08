package org.alnx.leetcode.medium;

public class ValidBST {

    private Integer current;
    public boolean isValidBST(TreeNode root) {
        current = null;
        return inorderValid(root);
    }

    private boolean inorderValid(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorderValid(root.left)) {
            return false;
        }
        if (current != null && root.val <= current) {
            return false;
        }
        current = root.val;
        return inorderValid(root.right);

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
