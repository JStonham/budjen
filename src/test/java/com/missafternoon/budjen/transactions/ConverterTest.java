package com.missafternoon.budjen.transactions;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    private Converter target = new Converter();

    @Test
    public void newTransactionCreated() {
        String[] args = new String[]{"add", "--debit", "--description", "fish", "--amount", "500"};
        Transaction transaction = target.convert(args);
        Assert.assertNotNull(transaction);
        assertEquals(TransactionType.DEBIT, transaction.getType());
        assertEquals("fish", transaction.getDescription());
        assertEquals(500, transaction.getMoney());
    }

    @Test
    public void inputInDifferentOrders_stillCreatesNewTransaction() {
        String[] args = new String[]{"add", "--amount", "2300", "--description", "refund", "--credit"};
        Transaction transaction = target.convert(args);
        assertEquals(2300, transaction.getMoney());
        assertEquals("refund", transaction.getDescription());
        assertEquals(TransactionType.CREDIT, transaction.getType());
    }

}

