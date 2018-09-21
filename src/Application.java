public class Application {

    // Hard-coded dependencies.
    private final TransactionData transactionData;
    private final Logger logger;

    // Injected dependencies
    private final Orderer orderer = new Orderer();
    private final TransactionFormatter transactionFormatter = new TransactionFormatter();

    public Application(final TransactionData data, final Logger logger) {
        this.transactionData = data;
        this.logger = logger;
    }

    public void start() {
        final Transaction[] data = transactionData.getTransactionData();
        for (final Transaction transaction : orderer.order(data)) {
            logger.print(transactionFormatter.format(transaction));
        }
    }

}