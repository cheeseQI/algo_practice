package com.myAlgorithms;

public class Converter {
    String input;
    final RuleChecker rule;

    public Converter(String in, RuleChecker r){
        this.input = in;
        this.rule = r;
    }

    public int atoi() { //neg/pos overflow, non-char
        int ans = 0;
        int curr = 0;
        boolean checkPos = true;

        if (input.charAt(0) == '-') {
            curr = 1;
            checkPos = false;
        }
        //int boundary = checkPos ? Integer.MAX_VALUE : Integer.MIN_VALUE; //abs(min)  = 1+ max

        while (curr < input.length()) {
            char c = input.charAt(curr);
            int checkInt = c - '0';
            /*
            if (checkInt < 0 || checkInt > 9) {
                throw new IllegalArgumentException("you have input a non-digit character: " + c);
            }
            if (checkPos && Integer.MAX_VALUE  - 10 * ans < checkInt) {
                throw new IllegalArgumentException("you have input a value larger than MAX_VALUE");
            }
            if (!checkPos && Integer.MAX_VALUE - 10 * ans < checkInt - 1) {
                throw new IllegalArgumentException("you have input a value smaller than MIN_VALUE");
            }
            */
            if (rule.checkValid(checkPos, checkInt, ans, c) != null) {
                throw new IllegalArgumentException(rule.checkValid(checkPos, checkInt, ans, c));
            }
            ans = checkInt + ans * 10;
            curr ++;
        }

        return checkPos ? ans : 0 - ans;
    }
}
