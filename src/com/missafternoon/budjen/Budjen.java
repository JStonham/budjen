package com.missafternoon.budjen;

import com.missafternoon.budjen.print.DefaultLogger;
import com.missafternoon.budjen.transactions.DefaultTransactionData;

public class Budjen {
    public static void main(String[] args) {
        new Application(new DefaultTransactionData(), new DefaultLogger()).start(args[0]);
    }
}