import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TransactionFormatterTest {

    private TransactionFormatter target = new TransactionFormatter();

    @Test
    public void testTransactionFormatterStringForDebit() {

        TransactionType type = TransactionType.DEBIT;
        String description = "Test";
        long money = -400;
        Date date = Date.from(LocalDate.of(2000, Month.JANUARY, 1)
                .atTime(LocalTime.NOON)
                .toInstant(ZoneOffset.UTC));

        final Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setMoney(money);
        transaction.setDate(date);

        String transactionAsAString = target.format(transaction);
        assertEquals("DEBIT, Test, £4.00, 2000-01-01", transactionAsAString);
    }

    @Test
    public void testTransactionFormatterStringForCredit() {

        TransactionType type = TransactionType.CREDIT;
        String description = "Test";
        long money = 7000;
        Date date = Date.from(LocalDate.of(2010, Month.DECEMBER, 31)
                .atTime(LocalTime.NOON)
                .toInstant(ZoneOffset.UTC));

        final Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setMoney(money);
        transaction.setDate(date);

        String transactionAsAString = target.format(transaction);
        assertEquals("CREDIT, Test, £70.00, 2010-12-31", transactionAsAString);
    }

}
