public class TransactionFormatter {

    private Currency currency = new Currency();
    private DateConverter dateConverter = new DateConverter();

    public String format(Transaction transaction) {
        TransactionType type = transaction.getType();
        String description = transaction.getDescription();
        String money = formatPounds(transaction.getMoney(),transaction.getType());
        String date = dateConverter.convert(transaction.getDate());
        return type+", "+description+", "+money+", "+date;
    }

    private String formatPounds(long money, final TransactionType type) {
        if (type == TransactionType.DEBIT) {
            money = -money;
        }
        return currency.formatPounds(money);
    }
}
