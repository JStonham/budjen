package com.missafternoon.budjen.transactions;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    @Test
    public void newTransactionCreated() {
        String[] args = new String[]{"add", "--debit", "--description", "fish", "--amount", "500"};
        Transaction transaction = new Converter().convert(args);
        Assert.assertNotNull(transaction);
        assertEquals(TransactionType.DEBIT, transaction.getType());
        assertEquals("fish", transaction.getDescription());
        assertEquals(500, transaction.getMoney());
    }

    @Test
    public void inputInDifferentOrders_stillCreatesNewTransaction() {
        String[] args = new String[]{"add", "--amount", "2300", "--description", "refund", "--credit"};
        Transaction transaction = new Converter().convert(args);
        assertEquals(2300, transaction.getMoney());
        assertEquals("refund", transaction.getDescription());
        assertEquals(TransactionType.CREDIT, transaction.getType());
    }

}

class Converter {

    Transaction convert(String[] args) {
        return makeTransaction(args);
    }

    private Transaction makeTransaction(String[] args) {
        final Transaction transaction = new Transaction();
        transaction.setType(findType(args));
        transaction.setDescription(findDescription(args));
        transaction.setMoney(findAmount(args));
        return transaction;
    }

    private TransactionType findType(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--credit".equals(args[i])) {
                return TransactionType.CREDIT;
            } else if ("--debit".equals(args[i])) {
                return TransactionType.DEBIT;
            }
        }
        return null;
    }

    private String findDescription(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--description".equals(args[i])) {
                String description = args[i + 1];
                return description;
            }
        }
        return null;
    }

    private long findAmount(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--amount".equals(args[i])) {
                String amount = args[i + 1];
                return Long.valueOf(amount);
            }
        }
        return 0;
    }
}