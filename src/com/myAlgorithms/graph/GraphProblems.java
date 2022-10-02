package com.myAlgorithms.graph;

import java.util.*;

public class GraphProblems {
    // [[in1, ], [out1, out2]]
    static Set<Integer> visited;
    static Map<Integer, List<Integer>> graph;
    static boolean hasFind;
    public static int findHeavier(List<List<Integer>> input) {
        // 1,2; 2,3; 1,3
        visited = new HashSet<>();
        graph = buildGraph(input);
        int lastOne = input.get(input.size() - 1).get(0); // former
        int lastTwo = input.get(input.size() - 1).get(1); // later
        findTarget(lastOne, lastTwo);
        if (hasFind) {
            return lastOne;
        }
        findTarget(lastTwo, lastOne);
        if (hasFind) {
            return lastTwo;
        }
        return -1;
    }

    private static Map<Integer, List<Integer>> buildGraph(List<List<Integer>> input) {
        int sz = input.size() - 1; // not include last one
        Map<Integer, List<Integer>> g = new HashMap<>();

        for (int i = 0; i < sz; i ++) {
            List<Integer> compareLine = input.get(i);
            int heavier = compareLine.get(0);
            int lighter = compareLine.get(1);
            List<Integer> curr =  g.getOrDefault(heavier, new LinkedList<>());
            curr.add(lighter);
            g.put(heavier, curr);
        }
        return g;
    }

    private static void findTarget(int curr, int target) {
        if (visited.contains(curr) || hasFind || !graph.containsKey(curr)) {
            return;
        }
        if (graph.get(curr).contains(target)) {
            hasFind = true;
            return;
        }
        visited.add(curr);
        for (int lighter: graph.get(curr)) {
            findTarget(lighter, target);
        }
        visited.remove(curr);
    }
}
