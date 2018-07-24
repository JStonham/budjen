public class Bank {
    private long balance;

    public long add(Transaction transaction) {
        balance = balance + transaction.getMoney();
        return balance;
    }
}
