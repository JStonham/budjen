package com.missafternoon.budjen.transactions;

class Currency {

    String formatPounds(final long i) {
        if (i == 0) {
            return "0";
        }
        final long remainder = i % 100;
        final String pence = remainder < 10 ? "0" + remainder : "" + remainder;
        return "£" + (i / 100) + "." + pence;
    }
}
