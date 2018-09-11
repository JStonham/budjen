import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Application {

    public void start() {
        Transaction[] transactions = getData();
        System.out.println();
    }

    public Transaction[] getData() {
        return new TransactionData().getTransactionData();
    }

    public Transaction[] orderTransactions(Transaction[] unorderedTransactions) {
        List<Transaction> transactionList = Arrays.asList(unorderedTransactions);
        transactionList.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return transactionList.toArray(new Transaction[unorderedTransactions.length]);
    }

    public void print() {

    }

}