package com.missafternoon.budjen.transactions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class CurrencyTest {

    private Currency target = new Currency();

    @Test
    private void givenZero_givesZero() {
        runTest("0", 0);
    }

    @Test
    private void given5Pounds_gives5Pounds() {
        runTest("£5.00", 500);
    }

    @Test
    private void given42Pence_gives42Pence() {
        runTest("£0.42", 42);
    }

    private void runTest(final String s, final int i) {
        assertEquals(s, target.formatPounds(i));
    }

}
