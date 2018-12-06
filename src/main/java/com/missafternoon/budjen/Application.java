package com.missafternoon.budjen;

import com.missafternoon.budjen.print.Logger;
import com.missafternoon.budjen.transactions.Converter;
import com.missafternoon.budjen.transactions.Orderer;
import com.missafternoon.budjen.transactions.Transaction;
import com.missafternoon.budjen.transactions.TransactionData;
import com.missafternoon.budjen.transactions.TransactionFormatter;
import com.missafternoon.budjen.transactions.Validator;

import java.util.List;

class Application {

    static final String HELP_MESSAGE = "" +
            "These are the commands available in budjen:\n" +
            "\n" +
            "   print - Prints a list of transactions.\n" +
            "   add - Adds a new transaction.\n" +
            "       --credit\n" +
            "       --debit\n" +
            "       --description 'any words'\n" +
            "       --amount 1200 [£12.00. Can be any positive value.]";

    private final TransactionData transactionData;
    private final Logger logger;

    private final Orderer orderer = new Orderer();
    private final TransactionFormatter transactionFormatter = new TransactionFormatter();
    private final Validator validator = new Validator();
    private final Converter converter = new Converter();

    Application(final TransactionData data, final Logger logger) {
        this.transactionData = data;
        this.logger = logger;
    }

    void start(String[] args) {
        final String arg = args.length > 0 ? args[0] : "help";
        if ("print".equals(arg)) {
            printTransactions();
        } else if ("help".equals(arg)) {
            logger.print(HELP_MESSAGE);
        } else if ("add".equals(arg)) {
            processAdd(args);
        } else {
            logger.print(makeErrorMessage(arg));
        }
    }

    private void processAdd(String[] args) {
        if (validator.validate(args)) {
            Transaction transaction = converter.convert(args);
            transactionData.getTransactionData().add(transaction);
        } else {
            logger.print("Invalid arguments given to add command.");
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