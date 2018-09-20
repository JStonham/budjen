public class TransactionData {

    private DateConverter dateConverter = new DateConverter();

    public Transaction[] getTransactionData() {
        return new Transaction[]{
                credit("Dress Refund", 2999, "2017-09-28"),
                credit("Pocket Money", 250, "2013-11-04"),
                credit("Lottery winnings", 5600000, "2017-12-25"),
                credit("TV Appearance", 100000, "2018-01-01"),
                credit("Book Deal", 500000, "2018-02-28"),
                credit("Poker winnings", 80000, "2017-05-15"),
                credit("Paper Round", 2300, "2014-03-17"),
                credit("Let flat", 420000, "2018-06-08"),

                debit("Lottery Ticket", -300, "2017-12-21"),
                debit("Train Ticket", -12330, "2017-07-16"),
                debit("Dog", -700000, "2015-09-30"),
                debit("Dog toys", -1549, "2015-10-02"),
                debit("Blue Audi", -4000000, "2018-04-03"),
                debit("Small Yacht", -5900099, "2018-05-07"),
                debit("Plastic Surgery", -2500000, "2018-06-13"),
                debit("Skiing holiday", -510000, "2018-01-28")
        };

    }

    private Transaction credit(final String description, final long money, final String date) {
        return makeTransaction(description, money, TransactionType.CREDIT, date);
    }

    private Transaction debit(final String description, final long money, final String date) {
        return makeTransaction(description, money, TransactionType.DEBIT, date);
    }

    private Transaction makeTransaction(final String description, final long money, final TransactionType type, final String date) {
        final Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setMoney(money);
        transaction.setDate(dateConverter.convert(date));
        return transaction;
    }
}