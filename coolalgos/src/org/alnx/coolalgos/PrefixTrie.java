package org.alnx.coolalgos;

import java.util.HashMap;
import java.util.Map;

public class PrefixTrie {

    static class Trie {
        boolean isTerminal;
        final Map<Character, Trie> children = new HashMap<>();

        public Trie() {
        }

        public void insert(String word) {
            rInsert(word);
        }

        private void rInsert(String word) {
            if (word == null || word.length() == 0) {
                this.isTerminal = true;
                return;
            }
            var head = word.charAt(0);
            var tail = word.substring(1);

            var child = children.get(head);
            if (child == null) {
                child = new Trie();
                children.put(head, child);
            }
            child.rInsert(tail);
        }

        public boolean search(String word) {
            var res = rSearch(word);
            return res != null && res.isTerminal;
        }

        private Trie rSearch(String word) {
            if (word == null || word.length() == 0) {
                return this;
            }
            var head = word.charAt(0);
            var tail = word.substring(1);
            if (!children.containsKey(head)) {
                return null;
            }
            var child = children.get(head);
            return child.rSearch(tail);
        }

        public boolean startsWith(String prefix) {
            var res = rSearch(prefix);
            return res != null;
        }
    }
}
