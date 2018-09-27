import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrdererTest {

    private Orderer target = new Orderer();
    private DateConverter dateConverter = new DateConverter();

    @Test
    public void ordersTransactions() {
        final Transaction yesterday = makeTransaction("2018-04-20");
        final Transaction today = makeTransaction("2018-05-15");
        final Transaction tomorrow = makeTransaction("2018-06-12");

        final List<Transaction> unorderedTransactions = Arrays.asList(yesterday, tomorrow, today);
        final List<Transaction> expecteds = Arrays.asList(yesterday, today, tomorrow);
        final List<Transaction> actual = target.order(unorderedTransactions);

        assertEquals(actual.size(), expecteds.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expecteds.get(i), actual.get(i));
        }
    }

    private Transaction makeTransaction(String date) {
        Transaction transaction = new Transaction();
        transaction.setDate(dateConverter.convert(date));
        return transaction;
    }

}
