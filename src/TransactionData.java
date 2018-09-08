public class TransactionData {
    public Transaction[] getTransactionData() {
        return new Transaction[]{
                credit("Dress Refund", 2999),
                credit("Pocket Money", 250),
                credit("Lottery winnings", 5600000),
                credit("TV Appearance", 100000),
                credit("Book Deal", 500000),
                credit("Poker winnings", 80000),
                credit("Paper Round", 2300),
                credit("Let flat", 420000),
                debit("Lottery Ticket", -300),
                debit("Train Ticket", -12330),
                debit("Dog", -700000),
                debit("Dog toys", -1549),
                debit("Blue Audi", -4000000),
                debit("Small Yacht", -5900099),
                debit("Plastic Surgery", -2500000),
                debit("Skiing holiday", -510000)
        };

    }

    private Transaction credit(final String description, final long money) {
        return makeTransaction(description, money, TransactionType.CREDIT);
    }

    private Transaction debit(final String description, final long money) {
        return makeTransaction(description, money, TransactionType.DEBIT);
    }

    private Transaction makeTransaction(final String description, final long money, final TransactionType type) {
        final Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setMoney(money);
        return transaction;
    }
}