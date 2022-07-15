package org.alnx.hackerrank.datastructures;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/java-dequeue/problem?isFullScreen=true
 */
public class DequeSolution {
    private static final Logger log = Logger.getLogger("dequesoln");
    public static void main(String[] args) {
        log.info("starting");
        var in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        var deque = new ArrayDeque<Integer>(m);
        long maxUnique;

        in.nextLine();
        String next = in.nextLine();
        log.info("read input");
        var ints = Arrays.stream(next.split(" ")).map(Integer::parseInt);

        // set initial values: first m elements and unique size
        List<Integer> intArray = ints.collect(Collectors.toList());
        log.info("parsed and converted input");
        deque.addAll(intArray.subList(0,m));
        Map<Integer, Integer> histogram = deque.stream().collect(Collectors.toMap((Integer k) -> k, (Integer val) -> 1, Integer::sum));
        maxUnique = histogram.size();
        log.info("set initial values");
        for (int i = 0; i+m < n; i++) {
            // shift deque across: remove from front, add new one to back
            int removed = deque.removeFirst();
            // update frequency of item, remove if no longer appearing
            int freq = histogram.get(removed) - 1;
            if (freq == 0) {
                histogram.remove(removed);
            } else {
                histogram.put(removed, freq);
            }
            Integer toAdd = intArray.get(i + m);
            deque.add(toAdd);
            histogram.merge(toAdd, 1,Integer::sum);
            maxUnique = Math.max(maxUnique, histogram.size());
        }
        log.info("iterations finished");
        System.out.println(maxUnique);
    }
}
