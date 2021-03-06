package com.missafternoon.budjen.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.missafternoon.budjen.formatting.Currency;

import java.util.Date;

public class Transaction {

    private TransactionType type;
    private String description;
    private long money;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
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

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public long getMoney() {
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
