import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Application {

    public void start() {
        Transaction[] transactions = new TransactionData().getTransactionData();
        Transaction[] orderedTransactions = orderTransactions(transactions);
        for (Transaction transaction : orderedTransactions) {
            print(transaction);
        }
    }

    public Transaction[] orderTransactions(Transaction[] unorderedTransactions) {
        List<Transaction> transactionList = Arrays.asList(unorderedTransactions);
        transactionList.sort(Comparator.comparing(Transaction::getDate));
        return transactionList.toArray(new Transaction[unorderedTransactions.length]);
    }

    public void print(Transaction transaction) {
        System.out.println(transaction.getType()+", "+transaction.getDescription()+", "+formatPounds(transaction.getMoney(),transaction.getType())+", "+formatDate(transaction.getDate()));
    }

    private String formatDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public String formatPounds(long money, final TransactionType type) {
        final Currency currency = new Currency();
        if (type == TransactionType.DEBIT) {
            money = -money;
        }
        return currency.formatPounds(money);
    }
}