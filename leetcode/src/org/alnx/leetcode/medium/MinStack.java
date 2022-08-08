package org.alnx.leetcode.medium;

import java.util.*;

public class MinStack {

    // need to support both normal stack ops
    // but also: retrieving the minimum.
    private final Deque<Integer> s;
    private final Queue<Integer> q;

    // idea: keep track of the minimum
    // upon addition -> check
    // upon removal
    public MinStack() {
        this.s = new ArrayDeque<>();
        this.q = new PriorityQueue<>();
    }

    public void push(int val) {
        s.push(val);
        q.add(val);
    }

    public void pop() {
        var rem = s.pop();
        q.remove(rem);
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return q.peek();
    }
}
