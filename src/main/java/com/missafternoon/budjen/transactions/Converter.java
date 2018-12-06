package com.missafternoon.budjen.transactions;

public class Converter {

    public Transaction convert(String[] args) {
        final Transaction transaction = new Transaction();
        transaction.setType(findType(args));
        transaction.setDescription(findDescription(args));
        transaction.setMoney(findAmount(args));
        return transaction;
    }

    private TransactionType findType(String[] args) {
        for (String arg : args) {
            if ("--credit".equals(arg)) {
                return TransactionType.CREDIT;
            } else if ("--debit".equals(arg)) {
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