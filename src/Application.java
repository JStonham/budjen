public class Application {

    TransactionData transactionData = new TransactionData();
    Orderer orderer = new Orderer();
    TransactionFormatter transactionFormatter = new TransactionFormatter();
    Logger logger = new Logger();

    public void start() {
        Transaction[] data = transactionData.getTransactionData();
        Transaction[] ordered = orderer.order(data);
        for (Transaction transaction : ordered) {
            String string = transactionFormatter.format(transaction);
            logger.print(string);
        }
    }

}