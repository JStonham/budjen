import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionFormatter {

    public String format(Transaction transaction) {
        TransactionType type = transaction.getType();
        String description = transaction.getDescription();
        String money = formatPounds(transaction.getMoney(),transaction.getType());
        String date = formatDate(transaction.getDate());
        return type+", "+description+", "+money+", "+date;
    }

    private String formatPounds(long money, final TransactionType type) {
        final Currency currency = new Currency();
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
