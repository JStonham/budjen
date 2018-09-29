import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DefaultTransactionDataTest {

    private DefaultTransactionData target = new DefaultTransactionData();

    @Test
    public void seedsData() {
        final List<Transaction> transactions = target.getTransactionData();
        assertEquals(16, transactions.size());
        for (final Transaction transaction : transactions) {
            assertNotEquals(0, transaction.getMoney());
            assertNotNull(transaction.getDescription());
            assertNotEquals("", transaction.getDescription());
            assertNotNull(transaction.getType());
            assertNotNull(transaction.getDate());
        }
    }
}
