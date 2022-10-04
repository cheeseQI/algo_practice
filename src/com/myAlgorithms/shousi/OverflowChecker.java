package com.myAlgorithms.shousi;

public class OverflowChecker extends RuleChecker{
    public OverflowChecker(RuleChecker next){
        super(next);
    }
    @Override
    protected String checkMyRule(boolean checkPos, int checkInt, int ans, char c) {
        if (checkPos && ans > (Integer.MAX_VALUE - checkInt) / 10) {
            return ("you have input a value larger than MAX_VALUE\n");
        }
        if (!checkPos && ans > (Integer.MAX_VALUE  + 1 - checkInt)/ 10 ) {
            return ("you have input a value smaller than MIN_VALUE\n");
        }
        return null;
    }
}
