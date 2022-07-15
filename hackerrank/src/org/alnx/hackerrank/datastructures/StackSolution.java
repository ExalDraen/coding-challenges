package org.alnx.hackerrank.datastructures;

import java.util.Scanner;
import java.util.Stack;

public class StackSolution {

    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        boolean res;

        while (sc.hasNext()) {
            String input=sc.next();
            res = checkBalanced(input);
            System.out.println(res);
        }
    }

    private static boolean checkBalanced(String input) {
        var parens = new Stack<Character>();
        Character top;

        for(Character c: input.toCharArray()) {
            // can always add open paren to stack
            switch (c) {
                case '{','(','[' -> parens.add(c);
                case '}',')',']' -> {
                    if (parens.empty()) return false;
                    top = parens.pop();
                    if (!matchingPair(top, c)) return false;
                }
            }
        }
        return parens.empty(); // should have consumed all parentheses by now.
    }

    private static boolean matchingPair(Character left, Character right) {
        switch(left) {
            case('{') -> { return right == '}';}
            case('[') -> {return right == ']';}
            case('(') -> {return right == ')';}
            default -> {return false;}
        }
    }
}
