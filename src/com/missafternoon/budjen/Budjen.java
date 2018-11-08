package com.missafternoon.budjen;

import com.missafternoon.budjen.print.DefaultLogger;
import com.missafternoon.budjen.transactions.DefaultTransactionData;

public class Budjen {
    public static void main(String[] args) {
        String arg;
        if (args.length == 0) {
            arg = null;
        }
        else {
            arg = args[0];
        }
        new Application(new DefaultTransactionData(), new DefaultLogger()).start(arg);
    }
}