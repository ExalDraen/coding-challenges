package org.alnx.coolalgos;

public class LLSorter {

    /**
     * Simple test rig to test linked list sorter
     */
    public static void main(String[] args) {
        // the mapping stuff is just to convert a char[] into a Character[]
        var testcases = new Character[][]{
                "abcde".chars().mapToObj(c -> (char) c).toArray(Character[]::new),
                "abcdefghijklm".chars().mapToObj(c -> (char) c).toArray(Character[]::new),
                "gcielbmhdjfak".chars().mapToObj(c -> (char) c).toArray(Character[]::new),
                "mlkjihgfedcba".chars().mapToObj(c -> (char) c).toArray(Character[]::new),
        };

        for (Character[] testcase : testcases) {
            LLNode<Character> ll = ArrayToLL(testcase);
            printLinkedList(ll);
            ll = sort(ll);
            printLinkedList(ll);
        }
    }

    private static <T extends Comparable<T>> LLNode<T> ArrayToLL(T[] array) {
        if (array.length == 0) {
            return null;
        }
        var head = new LLNode<>(array[0]);
        var curr = head;
        for (int i=1;i<array.length;i++) {
            curr.next = new LLNode<>(array[i]);
            curr = curr.next;
        }
        return head;
    }

    /**
     * Sort linked list using Mergesort, following Simon Tatham's algorithm
     * <p>
     * Note that the input list is modified and that the head of the list will most
     * likely change as a result of sorting. So the result of sorting should be assigned.
     * <pre>
     * {@code
     *  // correct:
     *  var sortedList = sort(myLinkedList);
     *  //incorrect:
     *  sort(myLinkedList);
     * }
     * </pre>
     *
     * @param head head of the linked list to sort
     * @param <T>  type of data stored in the linked list, must be comparable
     * @return head node of the newly sorted linked list
     * @see <a href="https://www.chiark.greenend.org.uk/~sgtatham/algorithms/listsort.html">
     * Simon Tatham's algo description</a>
     */
    public static <T extends Comparable<T>> LLNode<T> sort(LLNode<T> head) throws IllegalArgumentException {
        if (head == null) {
            throw new IllegalArgumentException("Can't sort null list"); // could also return null
        }

        var ret = head;
        LLNode<T> retTail;
        LLNode<T> toAdd;

        int psize;
        int qsize;
        int mergeCount;
        int k = 1;
        LLNode<T> p, q;

        // This algorithm treats the input as a collection of smaller sorted lists.
        // It does log N iterations, and in each iteration combines an adjacent pair
        // of sorted lists into one larger one.
        // Once we need to only do this once (i.e. our two sublists are each half of the list), we're done

        // In each pass of this loop, we are merging lists of size K into
        // lists of size 2K
        while (true) {
            // reset: we are beginning a new pass over our list from the beginning.
            mergeCount = 0;
            p = ret;
            retTail = null;
            ret = null;
            // If p is null, we've finished (reached the end of the LL)
            // otherwise, merge the next adjacent pair (p,q) of lists
            while (p != null) {
                mergeCount++;
                q = p;
                psize = 0;
                // step k steps away from p
                for (int l = 0; l < k; l++) {
                    q = q.next;
                    psize++;
                    if (q == null) {
                        break;
                    }
                }
                qsize = k;

                // We now have two adjacent lists p and q, each of size k (or less)
                // .... x x x x x x x x x x x x x x .....
                //      ^             ^
                //      p -- psize -| q -- qsize -|
                // q could become null if we run off the end of the list

                // Take from each of these lists in sort order and add them to
                // the output list.
                while (psize > 0 || (qsize > 0 && q != null)) {
                    // figure out which list we're going to take the element from
                    if (psize == 0) {
                        // p empty, must come from q
                        toAdd = q;
                        q = q.next;
                        qsize--;
                    } else if (qsize == 0 || q == null) {
                        // q is empty, must come from p
                        toAdd = p;
                        p = p.next;
                        psize--;
                    } else if (p.data.compareTo(q.data) <= 0) {
                        // p element is smallest, choose this one
                        toAdd = p;
                        p = p.next;
                        psize--;
                    } else {
                        // q element is smallest, choose that one
                        toAdd = q;
                        q = q.next;
                        qsize--;
                    }
                    // insert element into list
                    if (retTail == null) {
                        // special case when output list is empty
                        ret = toAdd;
                    } else {
                        retTail.next = toAdd;
                    }
                    retTail = toAdd;
                    retTail.next = null;
                }

                p = q;
            }
            // if we've only merged once, then that means we just merged two sublists
            // that cover the entire original list.
            // So the list is now sorted
            // Otherwise, double the size of sublists we are merging and start back
            // from the beginning of the list we've partially sorted.
            if (mergeCount == 1) {
                return ret;
            } else {
                k = 2 * k;
            }
        }
    }

    private static <T extends Comparable<T>> void printLinkedList(LLNode<T> head) {
        var curr = head;

        var out = new StringBuilder();
        out.append("[ ");
        while (curr != null) {
            out.append(curr.data.toString());
            out.append(", ");
            curr = curr.next;
        }
        out.append(" ]");
        out.append(" (head: ").append(head).append(")");
        System.out.println(out);
    }
}
