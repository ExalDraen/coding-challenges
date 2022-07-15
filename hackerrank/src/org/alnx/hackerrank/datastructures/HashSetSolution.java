package org.alnx.hackerrank.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class HashSetSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int num=Integer.parseInt(br.readLine());

        String current;
        var seen = new HashSet<String>();
        for(int i=0;i<num;i++) {
            current = br.readLine();
            seen.add(current);
            System.out.println(seen.size());
        }
    }
}
