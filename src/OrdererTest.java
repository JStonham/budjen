import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class OrdererTest {

    private Orderer target = new Orderer();
    private DateConverter dateConverter = new DateConverter();

    @Test
    public void ordersTransactions() {
        final Transaction yesterday = makeTransaction("2018-04-20");
        final Transaction today = makeTransaction("2018-05-15");
        final Transaction tomorrow = makeTransaction("2018-06-12");
        final Transaction[] unorderedTransactions = {yesterday, tomorrow, today};
        final Transaction[] expecteds = {yesterday, today, tomorrow};
        assertArrayEquals(expecteds, target.order(unorderedTransactions));
    }

    private Transaction makeTransaction(String date) {
        Transaction transaction = new Transaction();
        transaction.setDate(dateConverter.convert(date));
        return transaction;
    }

}
