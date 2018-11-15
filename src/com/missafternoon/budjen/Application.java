package com.missafternoon.budjen;

import com.missafternoon.budjen.print.Logger;
import com.missafternoon.budjen.transactions.Orderer;
import com.missafternoon.budjen.transactions.Transaction;
import com.missafternoon.budjen.transactions.TransactionData;
import com.missafternoon.budjen.transactions.TransactionFormatter;

import java.util.List;

public class Application {

    public static final String HELP_MESSAGE = "" +
            "These are the commands available in budjen:\n" +
            "\n" +
            "   print - Prints a list of transactions.";
    private final TransactionData transactionData;
    private final Logger logger;

    private final Orderer orderer = new Orderer();
    private final TransactionFormatter transactionFormatter = new TransactionFormatter();

    public Application(final TransactionData data, final Logger logger) {
        this.transactionData = data;
        this.logger = logger;
    }

    public void start(String[] args) {
        final String arg = args.length > 0 ? args[0] : "help";
        if ("print".equals(arg)) {
            printTransactions();
        } else if ("help".equals(arg)) {
            logger.print(HELP_MESSAGE);
        } else {
            logger.print(makeErrorMessage(arg));
        }
    }

    private void printTransactions() {
        final List<Transaction> data = transactionData.getTransactionData();
        for (final Transaction transaction : orderer.order(data)) {
            logger.print(transactionFormatter.format(transaction));
        }
    }

    private String makeErrorMessage(final String arg) {
        return "budjen '" + arg + "' is not a budjen command. See 'budjen help'.";
    }
}