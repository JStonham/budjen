package com.missafternoon.budjen.transactions;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void invalidateEmptyInput() {
        shouldFail();
    }

    @Test
    public void invalidateNotAdd() {
        shouldFail("subtract");
    }

    @Test
    public void validateCorrectInput() {
        String[] args = new String[]{"add", "--debit", "--description", "fish", "--amount", "500"};
        assertTrue(new Validator().validate(args));
    }

    @Test
    public void sixArgsOfGibberishIsInvalidated() {
        shouldFail("fish", "donkey", "correct", "horse", "battery", "staple");
    }

    @Test
    public void mostlyGibberishIsInvalidated() {
        shouldFail("add", "donkey", "correct", "horse", "battery", "staple");
    }

    @Test
    public void largelyGibberishIsInvalidated() {
        shouldFail("add", "--description", "correct", "horse", "battery", "staple");
    }

    @Test
    public void fiftyFiftyGibberishIsInvalidated() {
        shouldFail("add", "--description", "--amount", "horse", "battery", "staple");
    }

    @Test
    public void someGibberishIsInvalidated() {
        shouldFail("add", "--description", "--amount", "--debit", "--credit", "staple");
    }

    @Test
    public void orderOfParametersIsGibberishSoShouldBeInvalidated() {
        shouldFail("add", "--description", "--amount", "--debit", "battery", "staple");
    }

    @Test
    public void orderOfParametersIsGibberishAgainSoShouldBeInvalidated() {
        shouldFail("add", "--description", "I bought a dog", "--debit", "--amount", "staple"); // amount should be a number
    }

    private void shouldFail(String... args) {
        assertFalse(new Validator().validate(args));
    }
}

class Validator {

    boolean validate(String[] args) {
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
        if (!ifDescriptionIsFollowedByString(args)) {
            return false;
        }
        if (!ifAmountIsFollowedByNumber(args)) {
            return false;
        }
        return true;
    }

    private boolean ifDescriptionIsFollowedByString(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--description".equals(args[i])) {
                String next = args[i + 1];
                return !contains(new String[]{"--amount", "", "--credit", "--debit"}, next);
            }
        }
        return false;
    }

    private boolean ifAmountIsFollowedByNumber(String[] args) {
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
