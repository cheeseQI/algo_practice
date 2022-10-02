package com.myAlgorithms;

public class NoneDigitChecker extends RuleChecker{
    public NoneDigitChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(boolean checkPos, int checkInt, int ans, char c){
        if (checkInt < 0 || checkInt > 9) {
            return ("you have input a non-digit character: " + c);
        }
        return null;
    }
}
