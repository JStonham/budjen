package com.missafternoon.budjen.transactions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

class DefaultTransactionDataTest {

    private DefaultTransactionData target = new DefaultTransactionData();

    @Test
    private void seedsData() {
        final List<Transaction> transactions = target.getTransactionData();
        assertEquals(16, transactions.size());
        for (final Transaction transaction : transactions) {
            Assert.assertNotEquals(0, transaction.getMoney());
            assertNotNull(transaction.getDescription());
            Assert.assertNotEquals("", transaction.getDescription());
            assertNotNull(transaction.getType());
            assertNotNull(transaction.getDate());
        }
    }
}
