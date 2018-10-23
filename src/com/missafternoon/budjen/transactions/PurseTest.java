package com.missafternoon.budjen.transactions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurseTest {

    private static final long BIG_NUMBER = 64_000_000_000L;

    private Purse target = new Purse();

    @Test
    public void hasZeroBalanceByDefault() {
        assertEquals(0, target.getBalance());
    }

    @Test
    public void canHandleMultipleTransactions() {
        target.addTransaction(makeTransaction(128));
        target.addTransaction(makeTransaction(-256));
        target.addTransaction(makeTransaction(64));
        assertEquals(-64, target.getBalance());
    }

    @Test
    public void canHandleVeryLargeAmountsOfMoney() {
        target.addTransaction(makeTransaction(BIG_NUMBER));
        assertEquals(BIG_NUMBER, target.getBalance());
    }

    private Transaction makeTransaction(final long money) {
        final Transaction transaction = new Transaction();
        transaction.setMoney(money);
        return transaction;
    }
}
