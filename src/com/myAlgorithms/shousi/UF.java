package com.myAlgorithms.shousi;

import java.util.Arrays;

public class UF {
    int[] id;
    int[] sz;
    public int findRoot(int i) {
            while (i != id[i]) {
                // this line for path compression (modify parent idx to parent's parent idx), halving length!
                id[i] = id[id[i]];
                // find parent idx
                i = id[i];
            }
            return i;
    }
    public void friendUF(String[] query, int[] std1, int[] std2) {
        //query == 0 -> friend; query == 1 -> get sum
        id = new int[1000];
        sz = new int[1000];
        for (int i = 0; i < id.length; i ++) id[i] = i;
        Arrays.fill(sz, 1);
        for (int i = 0; i < query.length; i ++) {
            if (query[i].equals("Friend")) {
                union(std1[i], std2[i]);
            } else {
                int rootI = findRoot(std1[i]);
                int rootJ = findRoot(std2[i]);
                System.out.println(sz[rootI] + sz[rootJ]);
            }
        }
    }

    public void union(int i, int j) {
        int rootI = findRoot(i);
        int rootJ = findRoot(j);
        if (rootI == rootJ) {
            return;
        }
        if (sz[rootI] < sz[rootJ]) {
            id[rootI] = id[rootJ];
            sz[rootJ] += sz[rootI];
        } else {
            id[rootJ] = id[rootI];
            sz[rootI] += sz[rootJ];
        }
    }

    public boolean find(int i, int j) {
        return findRoot(i) == findRoot(j);
    }
}
