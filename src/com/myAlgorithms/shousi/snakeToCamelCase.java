package com.myAlgorithms.shousi;

import java.util.LinkedList;
import java.util.List;

public class snakeToCamelCase {
    // 将小写字母蛇形命名法改为驼峰命名法 code_play -> codePlay; CC_DD -> CC_DD
    public String[] convert(String[] input) {
        List<String> res = new LinkedList<>();
        for (String s: input) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i ++) {
                if (s.charAt(i) == '_' && i < s.length() - 1 && s.charAt(i + 1) == Character.toLowerCase(s.charAt(i + 1))) {
                    sb.append(Character.toUpperCase(s.charAt(i + 1)));
                    i ++;
                } else {
                    sb.append(s.charAt(i));
                }
            }
            res.add(sb.toString());
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i ++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
