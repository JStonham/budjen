package com.missafternoon.budjen.transactions;

public class DefaultLogger implements Logger {

    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
