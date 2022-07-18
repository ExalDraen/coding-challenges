package org.alnx.leetcode.easy;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LLCycle {

    public boolean hasCycle(ListNode head) {
        // well known trick
        // use two pointers that advance around the LL
        // one goes one tick, the other two ticks
        // if the faster one catches slower before end of list -> cycle
        // otherwise no cycle
        if (head == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        if (head.next == null) {
            return false;
        }

        var slower = head;
        var faster = head.next.next;
        while(slower.next != null && faster != null && faster.next != null && faster.next.next != null) {
            slower = slower.next;
            faster = faster.next.next;
            if (slower == faster || slower.next == faster) {
                return true;
            }
        }
        return false;
    }

  private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
  }
}
