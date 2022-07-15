package org.alnx.hackerrank.primes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n1=Integer.parseInt(br.readLine());
        int n2=Integer.parseInt(br.readLine());
        int n3=Integer.parseInt(br.readLine());
        int n4=Integer.parseInt(br.readLine());
        int n5=Integer.parseInt(br.readLine());
        checkPrime(n1);
        checkPrime(n1,n2);
        checkPrime(n1,n2,n3);
        checkPrime(n1,n2,n3,n4,n5);
    }

    /**
     check and print primes
     */
    public static void checkPrime (Integer... args) {
        var primes = Arrays.stream(args).filter(Solution::isPrime).map(Object::toString).collect(Collectors.joining(""));
        System.out.println(primes);
    }

    private static boolean isPrime(Integer num) {
        if (num ==1) return true;
        // naive solution: check if any int between 2 and sqrt(num) can evenly divide the number
        return IntStream.rangeClosed(2, (int) Math.sqrt(num)).noneMatch(x -> (num%x == 0));
    }
}
