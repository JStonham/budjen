public class Bank {
    private long balance;

    public long add(Transaction transaction) {
        balance += transaction.getMoney();
        return balance;
    }
}
