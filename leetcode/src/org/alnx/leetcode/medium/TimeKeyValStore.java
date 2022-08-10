package org.alnx.leetcode.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://leetcode.com/problems/time-based-key-value-store/">Key Value Store</a>
 */
public class TimeKeyValStore {
    static class TimeMap {
        private final Map<String, List<Entry>> values;

        // idea:
        // use a hashmap for key/val
        // value needs to store multiples, keep a list and append
        // then retriev with a search
        public TimeMap() {
            this.values = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            var cur = values.get(key);
            if (cur == null) {
                values.put(key, Stream.of(new Entry(value, timestamp)).collect(Collectors.toList()));
            } else {
                cur.add(new Entry(value, timestamp));
            }

        }

        public String get(String key, int timestamp) {
            var cur = values.get(key);
            if (cur == null) {
                return "";
            }
            // otherwise, binary search
            return search(cur, timestamp);
        }

        private static String search(List<Entry> entries, int timestamp) {
            int start = 0;
            int end = entries.size() - 1;
            String currentBest = "";
            while (start <= end) {
                int mid = start + (end - start) / 2;
                Entry midEntry = entries.get(mid);
                if (midEntry.timestamp == timestamp) {
                    return midEntry.value();
                }
                if (midEntry.timestamp <= timestamp) {
                    currentBest = midEntry.value;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return currentBest;
        }

        record Entry(String value, int timestamp) {

        }
    }
}
