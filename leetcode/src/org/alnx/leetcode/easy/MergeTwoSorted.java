package org.alnx.leetcode.easy;

public class MergeTwoSorted {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {

        public static void main(String[] args) {
            var res = mergeTwoLists(
                    new ListNode(1, new ListNode(2, new ListNode(4))),
                    new ListNode(1, new ListNode(3, new ListNode(4)))
                    );
        }

        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null && list2 == null) {
                return null;
            }
            // merge and keep sort order

            // one approach
            // iterate list 1 until next item >= current list 2 node
            // splice in node from 2 into 1
            // advance 1
            // advance 2
            // until we exhaust L1
            // add all remaining list 2 entries until list 2 exhausted

            ListNode curList1 = list1;
            ListNode curList2 = list2;
            ListNode res, resHead;

            if(curList1!=null && (curList2==null || curList1.val < curList2.val)) {
                res = new ListNode(curList1.val);
                resHead = res;
                curList1 = curList1.next;
            } else {
                res = new ListNode(curList2.val);
                resHead = res;
                curList2 = curList2.next;
            }

            while(curList1!=null) {
                if (curList2 == null || curList1.val < curList2.val) {
                    res.next = new ListNode(curList1.val);
                    res = res.next;
                    curList1 = curList1.next;
                } else {
                    res.next = new ListNode(curList2.val);
                    res = res.next;
                    curList2 = curList2.next;
                }
            }
            // we've exhausted List1, add remaining list2 entries (if any)
            if(curList2!=null) {
                // append to l1
                res.next = curList2;
            }
            return resHead;
        }
    }
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
}
