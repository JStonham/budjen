import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void givenNoData_returnsNoDataMessage() {
        String summary = new Transaction().getSummary();
        assertEquals("No data.", summary);
    }

    @Test
    public void givenMoney_returnsMonetaryDescription() {
        String summary = makeTransaction().getSummary();
        assertEquals("You have £10", summary);
    }

    @Test
    public void givenPositiveMoney_returnsMonetaryValue() {
        long money = makeTransaction().getMoney();
        assertEquals(10, money);
    }

    private Transaction makeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setMoney(10);
        return transaction;
    }
}