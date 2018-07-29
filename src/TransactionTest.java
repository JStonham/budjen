import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

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

    @Test
    public void givenNegativeMoney_returnsNegativeMonetaryValue() {
        long money = makeNegativeTransaction().getMoney();
        assertEquals(-20, money);
    }

    @Test
    public void givenNegativeMoney_returnsMonetaryDescription() {
        String summary = makeNegativeTransaction().getSummary();
        assertEquals("You have spent £20", summary);
    }

    private Transaction makeNegativeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setMoney(-20);
        return transaction;
    }

    @Test
    public void givenZeroMoney_returnsZeroMonetaryValue() {
        long money = new Transaction().getMoney();
        assertEquals(0, money);
    }

    @Test
    public void givenZeroMoney_returnsMonetaryDescription() {
        String summary = new Transaction().getSummary();
        assertEquals("No data.", summary);
    }

    @Test
    public void givenDescription_returnsDescription() {
        String description = makeDescription().getDescription();
        assertEquals("Rent",description);
    }

    private Transaction makeDescription() {
        Transaction transaction = new Transaction();
        transaction.setDescription("Rent");
        return transaction;
    }

    @Test
    public void givenEmptyDescription_returnsDescription() {
        String description = makeEmptyDescription().getDescription();
        assertEquals("",description);
    }

    private Transaction makeEmptyDescription() {
        Transaction transaction = new Transaction();
        transaction.setDescription("");
        return transaction;
    }

    @Test
    public void givenNullDescription_returnsNullDescription() {
        String description = new Transaction().getDescription();
        assertEquals(null,description);
    }

    @Test
    public void givenDate_returnDate() {
        Date date = new Date();
        Transaction transaction  = new Transaction();
        transaction.setDate(date);
        assertEquals(date, transaction.getDate());
    }

    @Test
    public void givenNullDate_returnDateToday() {
        Date today = new Date();
        Transaction transaction = new Transaction();
        transaction.setDate(null);
        assertEquals(today, transaction.getDate());
    }

    /**
     * Peter's test to highlight a bug when transaction date is set to null. Need to set the date during the setDate(null)
     * method rather than during the getDate() method.
     */
    @Test
    public void givenNoDate_assertTransactionDateIsTheDateTheTransactionWasCreated() {
        Date transactionCreated = new Date();
        Transaction transaction = makeTransaction();
        transaction.setDate(null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Assert.fail("Interrupted");
        }

        // Make a Date which is just after the transaction date was set to null
        Date justAfterTransactionDateSetToNull = new Date();
        justAfterTransactionDateSetToNull.setTime(transactionCreated.getTime() + 10);
        // Check that the Date of the transaction (when set to null) is the time the Date was set, not the time we get the Date.
        assertTrue(transaction.getDate().before(justAfterTransactionDateSetToNull));
    }
}