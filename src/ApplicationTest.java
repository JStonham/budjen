import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationTest {

    private Application target = new Application();

    @Test
    public void ordersTransactions() {
        final Transaction yesterday = makeTransaction("2018-09-10");
        final Transaction today = makeTransaction("2018-09-11");
        final Transaction tomorrow = makeTransaction("2018-09-12");
        final Transaction[] unorderedTransactions = {yesterday, tomorrow, today};
        final Transaction[] expecteds = {yesterday, today, tomorrow};
        assertArrayEquals(expecteds, target.orderTransactions(unorderedTransactions));
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
