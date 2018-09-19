import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionFormatter {

    Currency currency = new Currency();

    public String[] format(Transaction[] transactions) {
        String[] formattedTransactions = new String[transactions.length];
        for (int i = 0; i < transactions.length; i++) {
            Transaction transaction = transactions[i];
            String formatted = format(transaction);
            formattedTransactions[i] = formatted;
        }
        return formattedTransactions;
    }

    public String format(Transaction transaction) {
        TransactionType type = transaction.getType();
        String description = transaction.getDescription();
        String money = formatPounds(transaction.getMoney(),transaction.getType());
        String date = formatDate(transaction.getDate());
        return type+", "+description+", "+money+", "+date;
    }

    private String formatPounds(long money, final TransactionType type) {
        if (type == TransactionType.DEBIT) {
            money = -money;
        }
        return currency.formatPounds(money);
    }

    private String formatDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
