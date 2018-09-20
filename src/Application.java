public class Application {

    TransactionData transactionData =new TransactionData();
    Orderer orderer = new Orderer();
    TransactionFormatter transactionFormatter = new TransactionFormatter();
    Logger logger = new Logger();

    public void start() {
        Transaction[] data = transactionData.getTransactionData();
        Transaction[] ordered = orderer.order(data);
        String[] strings = transactionFormatter.format(ordered);
        logger.print(strings);
    }

}