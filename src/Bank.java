public class Bank {

    private long balance = 0;

    public long getBalance() {
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        balance += transaction.getMoney();
    }
}
