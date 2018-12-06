package com.missafternoon.budjen.transactions;

import com.missafternoon.budjen.formatting.DateConverter;

import java.util.ArrayList;
import java.util.List;

public class DefaultTransactionData implements TransactionData {

    private DateConverter dateConverter = new DateConverter();
    private List<Transaction> transactions = new ArrayList<>();

    public DefaultTransactionData() {
        transactions.add(credit("Dress Refund", 2999, "2017-09-28"));
        transactions.add(credit("Pocket Money", 250, "2013-11-04"));
        transactions.add(credit("Lottery winnings", 5600000, "2017-12-25"));
        transactions.add(credit("TV Appearance", 100000, "2018-01-01"));
        transactions.add(credit("Book Deal", 500000, "2018-02-28"));
        transactions.add(credit("Poker winnings", 80000, "2017-05-15"));
        transactions.add(credit("Paper Round", 2300, "2014-03-17"));
        transactions.add(credit("Let flat", 420000, "2018-06-08"));

        transactions.add(debit("Lottery Ticket", -300, "2017-12-21"));
        transactions.add(debit("Train Ticket", -12330, "2017-07-16"));
        transactions.add(debit("Dog", -700000, "2015-09-30"));
        transactions.add(debit("Dog toys", -1549, "2015-10-02"));
        transactions.add(debit("Blue Audi", -4000000, "2018-04-03"));
        transactions.add(debit("Small Yacht", -5900099, "2018-05-07"));
        transactions.add(debit("Plastic Surgery", -2500000, "2018-06-13"));
        transactions.add(debit("Skiing holiday", -510000, "2018-01-28"));
    }

    @Override
    public List<Transaction> getTransactionData() {
        return transactions;
    }

    private Transaction credit(final String description, final long money, final String date) {
        return makeTransaction(description, money, TransactionType.CREDIT, date);
    }

    private Transaction debit(final String description, final long money, final String date) {
        return makeTransaction(description, money, TransactionType.DEBIT, date);
    }

    private Transaction makeTransaction(final String description, final long money, final TransactionType type, final String date) {
        final Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setMoney(money);
        transaction.setDate(dateConverter.convert(date));
        return transaction;
    }
}