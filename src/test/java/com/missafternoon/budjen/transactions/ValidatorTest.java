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


