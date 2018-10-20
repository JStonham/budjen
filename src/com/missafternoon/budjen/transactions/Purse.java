package com.missafternoon.budjen.transactions;

class Purse {

    private long balance = 0;

    long getBalance() {
        return balance;
    }

    void addTransaction(Transaction transaction) {
        balance += transaction.getMoney();
    }
}
