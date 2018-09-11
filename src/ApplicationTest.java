import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationTest {

    private Application target = new Application();

    @Test
    public void returnsData() {
        final Transaction[] transactions = target.getData();
        assertEquals(16, transactions.length);
        for (final Transaction transaction : transactions) {
            assertNotEquals(0, transaction.getMoney());
            assertNotNull(transaction.getDescription());
            assertNotEquals("", transaction.getDescription());
            assertNotNull(transaction.getType());
            assertNotNull(transaction.getDate());
        }
    }

    @Test
    public void ordersTransactions() {
        final Transaction yesterday = makeTransaction("2018-09-10");
        final Transaction today = makeTransaction("2018-09-11");
        final Transaction tomorrow = makeTransaction("2018-09-12");
        final Transaction[] unorderedTransactions = new Transaction[] {yesterday, tomorrow, today};
        final Transaction[] transactions = target.orderTransactions(unorderedTransactions);
        assertNotNull(transactions);
        assertEquals(3, transactions.length);
        assertEquals(yesterday,transactions[0]);
        assertEquals(today,transactions[1]);
        assertEquals(tomorrow,transactions[2]);
    }

    private Transaction makeTransaction(String date) {
        Transaction transaction = new Transaction();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            transaction.setDate(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return transaction;
    }
}
