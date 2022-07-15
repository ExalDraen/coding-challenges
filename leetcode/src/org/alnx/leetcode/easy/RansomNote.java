package org.alnx.leetcode.easy;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/ransom-note/
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        // one approach
        // construct histogram of letters in magazine
        // and letters in ransomNote

        // for each entry in ransom note histogram, check if corresponding entry
        // in magazine is >= -> we have enough letters to make the ransom note

        var histS = new HashMap<Character, Integer>();

        for (Character c: magazine.toCharArray()) {
            histS.put(c, histS.getOrDefault(c,0) + 1);
        }

        for (Character c: ransomNote.toCharArray()) {
            int available = histS.getOrDefault(c,0);
            if(available <= 0) {
                return false;
            }
            histS.put(c, available - 1);
        }
        return true;
    }
}
