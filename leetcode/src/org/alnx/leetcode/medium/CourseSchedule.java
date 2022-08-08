package org.alnx.leetcode.medium;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/course-schedule/submissions/">Course Schedule</a>
 */
public class CourseSchedule {

    public static void main(String[] args) {
        var tc0 = new int[][]{
                new int[]{1, 4},
                new int[]{2, 4},
                new int[]{3, 1},
                new int[]{3, 2},
        };
        var num0 = 5;
        var tc1 = new int[][]{
                new int[]{2, 0},
                new int[]{1, 0},
                new int[]{3, 1},
                new int[]{3, 2},
                new int[]{1, 3}
        };
        var num1 = 4;
        printTest(num0, tc0);
        printTest(num1, tc1);
    }

    private static void printTest(int numCourses, int[][] pre) {
        System.out.printf("Input: Num = %d, Prereq = %s\nCan Finish? %s\n", numCourses, Arrays.deepToString(pre), canFinish(numCourses, pre));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }

        // map of course: courses it depends on;
        var adjList = new HashMap<Integer, List<Integer>>();

        for (int[] pre : prerequisites) {
            var cur = pre[0];
            List<Integer> sub;

            sub = adjList.getOrDefault(cur, new ArrayList<>());
            for (int j = 1; j < pre.length; j++) {
                sub.add(pre[j]);
            }
            adjList.put(cur, sub);
        }

        // check cycle for each course
        for (int c = 0; c < numCourses; c++) {
            if (hasCycle(adjList, c)) {
                return false;
            }
        }

        return true;
        // n courses, can I finish them all?

        // [1,0] 0 first, then 1 0->1
        // [0,1] 1 first, then 0 1->0

        // can I build a graph without a cycle?
    }

    private static boolean hasCycle(Map<Integer, List<Integer>> adjList, int start) {
        if (adjList.size() == 0) {
            return false;
        }
        //    3
        //   /  2
        //  1==/

        // dfs with breadcrumb
        var breadcrumbs = new HashSet<Integer>();
        // and with a set of nodes we've already checked
        var checked = new HashSet<Integer>();

        return dfs(adjList, start, breadcrumbs, checked);
    }

    private static boolean dfs(Map<Integer, List<Integer>> adjList, int start, Set<Integer> seen, Set<Integer> checked) {
        if (seen.contains(start)) {
            return true;
        }
        if (!adjList.containsKey(start)) {
            return false;
        }
        if (checked.contains(start)) {
            return false;
        }
        seen.add(start);
        // follow all children and check for cycles
        for (var adj : adjList.get(start)) {
            if (dfs(adjList, adj, seen, checked)) {
                return true;
            }
        }
        // didn't find a cycle, we can remove our currently visited node
        seen.remove(start);
        checked.add(start);
        return false;
    }
}
