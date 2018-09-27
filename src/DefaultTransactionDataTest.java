import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultTransactionDataTest {

    private DefaultTransactionData target = new DefaultTransactionData();

    @Test
    public void seedsData() {
        final Transaction[] transactions = target.getTransactionData();
        assertEquals(16, transactions.length);
        for (final Transaction transaction : transactions) {
            assertNotEquals(0, transaction.getMoney());
            assertNotNull(transaction.getDescription());
            assertNotEquals("", transaction.getDescription());
            assertNotNull(transaction.getType());
            assertNotNull(transaction.getDate());
        }
    }
}