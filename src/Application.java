import sun.java2d.loops.TransformBlit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
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
        System.out.println(transaction.getType()+", "+transaction.getDescription()+", "+formatPounds(transaction.getMoney())+", "+formatDate(transaction.getDate()));
    }

    private String formatDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public String formatPounds(long money) {
        if (money == 0) {
            return "0";
        }
        if (money <0) {
            final long remainder = (money % 100) * -1;
            final String pence = remainder < 10 ? "0" + remainder : "" + remainder;
            return "£" + ((money / 100) * -1) + "." + pence;
        }
        final long remainder = money % 100;
        final String pence = remainder < 10 ? "0" + remainder : "" + remainder;
        return "£" + (money / 100) + "." + pence;
    }
}