package com.missafternoon.budjen;

import com.missafternoon.budjen.transactions.TransactionData;
import com.missafternoon.budjen.print.Logger;
import com.missafternoon.budjen.transactions.Orderer;
import com.missafternoon.budjen.transactions.TransactionFormatter;
import com.missafternoon.budjen.transactions.Transaction;
import java.util.List;

public class Application {

    private final TransactionData transactionData;
    private final Logger logger;

    private final Orderer orderer = new Orderer();
    private final TransactionFormatter transactionFormatter = new TransactionFormatter();

    public Application(final TransactionData data, final Logger logger) {
        this.transactionData = data;
        this.logger = logger;
    }

    public void start() {
        final List<Transaction> data = transactionData.getTransactionData();
        for (final Transaction transaction : orderer.order(data)) {
            logger.print(transactionFormatter.format(transaction));
        }
    }

}