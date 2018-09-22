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
        final Transaction[] data = transactionData.getTransactionData();
        for (final Transaction transaction : orderer.order(data)) {
            logger.print(transactionFormatter.format(transaction));
        }
    }

}