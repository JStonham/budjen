public class TransactionData {

    private Transaction returnedDressRefund() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription("Dress Refund");
        transaction.setMoney(3000);
        return transaction;
    }

    private Transaction pocketMoney() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription("Pocket Money");
        transaction.setMoney(250);
        return transaction;
    }

    private Transaction winTheLottery() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription("Lottery winnings");
        transaction.setMoney(5600000);
        return transaction;
    }

    private Transaction buyATrainTicket() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription("Train Ticket");
        transaction.setMoney(-12300);
        return transaction;
    }

    private Transaction buyADog() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription("Dog");
        transaction.setMoney(-70000);
        return transaction;
    }

    private Transaction buyASmallYacht() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription("Small Yacht");
        transaction.setMoney(-4500000);
        return transaction;
    }
}
