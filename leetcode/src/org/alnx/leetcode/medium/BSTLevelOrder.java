package org.alnx.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BSTLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // to get level order traversal
        // use a queue and recurse
        var ret = new ArrayList<List<Integer>>();
        var q = new ArrayDeque<TreeNode>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            var sub = new ArrayList<Integer>();
            int level_length = q.size();
            for (int i = 0; i < level_length; i++) {
                var cur = q.pop();
                sub.add(cur.val);
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            ret.add(sub);
        }
        return ret;
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
