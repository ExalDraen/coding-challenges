package org.alnx.hackerrank.datastructures;

import java.util.*;

public class PQSolution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }

    static class Student implements Comparable<Student>{
        private final int id;
        private final String name;
        private final double cgpa;

        public Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public double getCgpa() {
            return cgpa;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        @Override
        public int compareTo(Student o) {
            if(o.getCgpa() != cgpa) {
                return cgpa > o.getCgpa() ? 1 : -1;
            }
            if(!o.getName().equals(name)) {
                //return name.compareTo(o.getName());
                return o.getName().compareTo(name);
            }
            if(o.getId() != id) {
                return id > o.getId() ? 1 : -1;
            }
            return 0;
        }
    }

    static class Priorities {
        List<Student> getStudents(List<String> events) {
            var pq = new PriorityQueue<Student>(Comparator.reverseOrder());
            String[] bits;
            Student cur;
            for(String e: events) {
                bits = e.split(" ");
                if (bits[0].equals("ENTER")) {
                    cur = new Student(Integer.parseInt(bits[3]), bits[1], Double.parseDouble(bits[2]));
                    pq.add(cur);
                } else {
                    pq.poll();
                }
            }
            ArrayList<Student> ret = new ArrayList<>(pq.size());
            while(!pq.isEmpty()) {
                ret.add(pq.poll());
            }
            return ret;
        }
    }
}
