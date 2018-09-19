import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertArrayEquals;

public class OrdererTest {

    private Orderer target = new Orderer();

    @Test
    public void ordersTransactions() {
        final Transaction yesterday = makeTransaction("2018-04-20");
        final Transaction today = makeTransaction("2018-05-15");
        final Transaction tomorrow = makeTransaction("2018-06-12");
        final Transaction[] unorderedTransactions = {yesterday, tomorrow, today};
        final Transaction[] expecteds = {yesterday, today, tomorrow};
        assertArrayEquals(expecteds, target.orderTheTransactions(unorderedTransactions));
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
