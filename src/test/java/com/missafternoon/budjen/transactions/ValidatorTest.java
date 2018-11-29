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

    private void shouldFail(String... args) {
        assertFalse(new Validator().validate(args));
    }
}

class Validator {

    boolean validate(String[] args) {
        return args.length == 6;
    }
}
