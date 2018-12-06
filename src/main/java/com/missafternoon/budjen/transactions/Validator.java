package com.missafternoon.budjen.transactions;

public class Validator {

    public boolean validate(String[] args) {
        if (args.length != 6) {
            return false;
        }
        if (!("add".equals(args[0])
                && contains(args, "--description")
                && contains(args, "--amount")
                && (contains(args, "--credit") ^ contains(args, "--debit"))
        )) {
            return false;
        }
        if (!DescriptionIsFollowedByString(args)) {
            return false;
        }
        return AmountIsFollowedByNumber(args);
    }

    private boolean DescriptionIsFollowedByString(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--description".equals(args[i])) {
                String next = args[i + 1];
                return !contains(new String[]{"--amount", "", "--credit", "--debit"}, next);
            }
        }
        return false;
    }

    private boolean AmountIsFollowedByNumber(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--amount".equals(args[i])) {
                String next = args[i + 1];
                return isNumber(next);
            }
        }
        return false;
    }

    private boolean contains(String[] args, String expectedFlag) {
        for (String arg : args) {
            if (expectedFlag.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(String arg) {
        try {
            Long.valueOf(arg);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}