package com.myAlgorithms.shousi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class stringSimplify {
    // "/a/b/c/../d"
    // "/a/b/e"
    // "/a/b/c/d"
    // return deepest common dir "/a/b"
    public String getDir(String[] paths) {
        String res = "";
        List<String> simplifiedPaths = new ArrayList<>();
        int minL = Integer.MAX_VALUE;
        for (String path: paths) {
            StringBuilder simplified = new StringBuilder();
            String[] p = path.split("/");
            Deque<String> stack = new LinkedList<>();
            stack.push(p[1]);
            for (int i = 2; i < p.length; i ++) {
                if (p[i].equals("..")) {
                    stack.pop();
                } else {
                    stack.push(p[i]);
                }
            }

            while (!stack.isEmpty()){
                simplified.insert(0,"/");
                simplified.insert(0,stack.pop());
            }
            minL = Math.min(minL, simplified.length());
            simplifiedPaths.add(simplified.toString());
        }

        for (int i = 0; i < minL; i ++) {
            char target = simplifiedPaths.get(0).charAt(i);
            for (String sp: simplifiedPaths) {
                if (sp.charAt(i) != target) {
                    res = simplifiedPaths.get(0).substring(0, i);
                    return res;
                }
            }
        }
        return res;
    }
}
