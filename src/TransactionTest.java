import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void givenNoDataOrMoney_returnsNoDataOrMoney() {
        String summary = new Transaction().getSummary();
        long money = new Transaction().getMoney();
        String description = new Transaction().getDescription();
        assertEquals("No data.", summary);
        assertEquals(0, money);
        assertNull(null,description);
    }

    @Test
    public void givenMoney_returnsDescriptionAndValue() {
        String summary = makeTransaction().getSummary();
        long money = makeTransaction().getMoney();
        assertEquals("Your account has been credited with £10.00", summary);
        assertEquals(1000, money);
    }

    @Test
    public void givenNegativeMoney_returnsNegativeDescriptionAndValue() {
        String summary = makeNegativeTransaction().getSummary();
        String summary2 = makeNegativeTransaction2().getSummary();
        long money = makeNegativeTransaction().getMoney();
        assertEquals("You have spent £20.00", summary);
        assertEquals("You have spent £20.01", summary2);
        assertEquals(-2000, money);
    }

    @Test
    public void givenDescription_returnsDescription() {
        String description = makeDescription().getDescription();
        assertEquals("Rent",description);
    }

    @Test
    public void givenDate_returnDate() {
        Date date = new Date();
        Date today = new Date();
        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setDate(null);
        assertEquals(date, transaction.getDate());
        assertEquals(today, transaction.getDate());
    }

    private Transaction makeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setMoney(1000);
        return transaction;
    }

    private Transaction makeNegativeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setMoney(-2000);
        return transaction;
    }

    private Transaction makeDescription() {
        Transaction transaction = new Transaction();
        transaction.setDescription("Rent");
        return transaction;
    }

    private Transaction makeNegativeTransaction2() {
        Transaction transaction = new Transaction();
        transaction.setMoney(-2001);
        return transaction;
    }
}