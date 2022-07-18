package org.alnx.prep;

public class LevenshteinDistance {

    public static void main(String[] args) {
        printDistance("left", "right");
        printDistance("cat", "caterpillar");
        printDistance("caterpillar", "cat");
        printDistance("tal", "undertale");
        printDistance("busa", "ebcar");
        printDistance("kimwhohe", "jicwil");
        printDistance("cegaga", "ki");
        printDistance("fe", "piw");
        printDistance("lesocareb", "derubeze");
    }

    private static void printDistance(String left, String right) {
        System.out.printf("%s, %s : %s\n", left, right, getEditDistance(left, right));

    }
    // levenshtein edit distance
    // number of transformations edits needed for string
    //

    // first observation:
    // + add 1 for every size difference
    // cat
    // latititude
    // +7 just for size difference

    // for the remaining
    // iterate over the shorter string
    // compare indices, add 1 for every difference

    // alignment?
    //
    // cat
    // undercat
    //      cat

    // order matters, so can't just use a hash table
    // naive algorithm O(n*m) n (larger string), m (smaller string)
    // try all alignments
    // pick the lowest
    //  cat
    // undercat

    // can we do better?

    public static int getEditDistance(String left, String right) {
        char[] leftChars;
        char[] rightChars;
        if (left.length() > right.length()) {
            leftChars = left.toCharArray();
            rightChars = right.toCharArray();
        } else {
            rightChars = left.toCharArray();
            leftChars = right.toCharArray();
        }
        int minDistance = 10000;

        // left moves along, right stays
        for (int i = 0; i < leftChars.length; i++) {
            int curDistance = 0;
            for (int j = 0; j < rightChars.length; j++) {
                if (((i + j) >= leftChars.length) || (leftChars[i + j] != rightChars[j])) {
                    curDistance++;
                }
            }
            curDistance += leftChars.length - rightChars.length;
            if (curDistance < minDistance) {
                minDistance = curDistance;
            }
        }

        // example 1:
        // left =  cat
        // right = caterpillar

        // example 2:
        // left =   undertale
        // right =  tal

        return minDistance;
    }
}
