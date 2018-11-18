package com.missafternoon.budjen.transactions;

import com.missafternoon.budjen.formatting.Currency;

import java.util.Date;

public class Transaction {

    private TransactionType type;
    private String description;
    private long money;
    private Date date = new Date();

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
        }
    }

    TransactionType getType() {
        return type;
    }

    String getDescription() {
        return description;
    }

    Date getDate() {
        return date;
    }

    long getMoney() {
        return money;
    }

    String getSummary() {
        if (money == 0) {
            return "No data.";
        }
        if (money < 0) {
            return "You have spent " + new Currency().formatPounds(-money);
        }
        return "Your account has been credited with " + new Currency().formatPounds(money);
    }
}