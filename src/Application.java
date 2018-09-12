import sun.java2d.loops.TransformBlit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Application {

    public void start() {
        Transaction[] transactions = getData();
        Transaction[] orderedTransactions = orderTransactions(transactions);
        for (Transaction transaction : orderedTransactions) {
            print(transaction);
        }
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

    public void print(Transaction transaction) {
        System.out.println(transaction.getType()+", "+transaction.getDescription()+", "+transaction.getMoney()+", "+transaction.getDate());
    }

}