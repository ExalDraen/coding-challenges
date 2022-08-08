package org.alnx.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">Longest substring
 * without repeating characters</a>
 * <p>
 * s -> longest substring without repeats
 * <p>
 * beethoven:
 * b       -> 1
 * be      -> 2
 * bee     -> 2 (be)
 * beet    -> 2 (be)
 * beeth   -> 3 (eth)
 */
public class LongestSubstring {


    public static void main(String[] args) {
        var tc1 = "beethoven";
        var tc2 = "abcabcbb";
        var tc3 = "pwwkew";
        var tc4 = "x";
        printTc(tc2);
        printTc(tc1);
        printTc(tc3);
        printTc(tc4);
    }

    private static void printTc(String s) {
        System.out.printf("Input: '%s' => %d (window) \n", s, lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int sLen = s.length();
        if (sLen == 0 || sLen == 1) {
            return sLen;
        }

        return windowSoln(s);
    }

    /**
     * Use a sliding window to find maximum:
     * - for given end index 'right', keep dropping left char until unique
     */
    private static int windowSoln(String s) {
        // for given end index j
        // drop left characters until i,j unique
        int maxSoFar = 1;

        var chars = new HashMap<Character, Integer>();

        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r, 0) + 1);

            // newly added character is a dupe.
            // drop left until it is no longer a dupe.
            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }
            maxSoFar = Math.max(right - left + 1, maxSoFar);
            // expand window to the right
            right++;
        }
        return maxSoFar;
    }

    /**
     * doesn't work :)
     */
    private static int dpSoln(String s) {
        int maxSoFar = 1;
        int[] maxSubs = new int[s.length()];
        maxSubs[0] = 1;

        for (int i = 1; i < maxSubs.length; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                maxSubs[i] = 1;
            } else {
                maxSubs[i] = maxSubs[i - 1] + 1;
            }
            maxSoFar = Math.max(maxSubs[i], maxSoFar);
        }
        return maxSoFar;
    }

    /**
     * doesn't work :)
     */
    private static int quadraticSoln(String s) {
        int sLen = s.length();
        String candidate = findSubstring(s, sLen);
        return candidate.length();
    }

    private static String findSubstring(String s, int sLen) {
        String candidate = s;
        for (int i = sLen; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                candidate = s.substring(j, i);
                if (!(hasDupes(candidate))) {
                    return candidate;
                }
            }
        }
        return candidate;
    }

    private static boolean hasDupes(String s) {
        var seen = new HashSet<Character>();
        for (var c : s.toCharArray()) {
            if (seen.contains(c)) {
                return true;
            }
            seen.add(c);
        }
        return false;
    }
}
