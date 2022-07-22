package org.alnx.coolalgos;

/**
 * Super simple linked list node, just to show linked list sorting
 */
public class LLNode <T extends Comparable<T>> {
    T data;
    LLNode<T> next;

    public LLNode(T data, LLNode<T> next) {
        this.data = data;
        this.next = next;
    }
    public LLNode(T data) {
        this(data,null);
    }

    @Override
    public String toString() {
        return "LLNode{" +
                "data=" + data +
                '}';
    }
}
