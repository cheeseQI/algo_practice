package com.myAlgorithms.shousi;

public abstract class RuleChecker {
    final RuleChecker next;

    public RuleChecker(RuleChecker next) {
       this.next = next;
    }

    protected abstract String checkMyRule(boolean checkPos, int checkInt, int ans, char c);

    public String checkValid(boolean checkPos, int checkInt, int ans, char c) {
        if (checkMyRule(checkPos, checkInt, ans, c) != null) {
            return checkMyRule(checkPos, checkInt, ans, c);
        }
        if (next != null) return next.checkValid(checkPos, checkInt, ans, c);
        return null;
    }
}
