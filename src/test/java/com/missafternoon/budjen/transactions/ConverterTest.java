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

class Converter {

    Transaction convert(String[] args) {
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
        throw new RuntimeException("Could not find Transaction Type.");
    }


    private String findDescription(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--description".equals(args[i])) {
                return args[i + 1];
            }
        }
        return "<Absent>";
    }

    private long findAmount(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("--amount".equals(args[i])) {
                return Long.valueOf(args[i + 1]);
            }
        }
        throw new RuntimeException("Could not find Amount.");
    }
}