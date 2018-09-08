import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void givenNoDataOrMoney_returnsNoDataOrMoney() {
        final Transaction transaction = new Transaction();
        final String summary = transaction.getSummary();
        final long money = transaction.getMoney();
        final String description = transaction.getDescription();
        final TransactionType type = transaction.getType();
        assertEquals("No data.", summary);
        assertEquals(0, money);
        assertNull(description);
        assertNull(type);
    }

    @Test
    public void givenMoney_returnsDescriptionAndValueAndType() {
        final String summary = makeTransaction().getSummary();
        final long money = makeTransaction().getMoney();
        final TransactionType type = makeTransaction().getType();
        assertEquals("Your account has been credited with £10.00", summary);
        assertEquals(1000, money);
        assertEquals(TransactionType.CREDIT, type);
    }

    @Test
    public void givenRoundNegativeMoney_returnsRoundDetails() {
        final Transaction transaction = makeNegativeTransaction();
        assertEquals("You have spent £20.00", transaction.getSummary());
        assertEquals(-2000, transaction.getMoney());
        assertEquals(TransactionType.DEBIT, transaction.getType());
    }

    @Test
    public void givenDecimalNegativeMoney_returnsDecimalDetails() {
        final Transaction transaction = makeNegativeTransaction2();
        assertEquals("You have spent £20.01", transaction.getSummary());
        assertEquals(-2001, transaction.getMoney());
        assertEquals(TransactionType.DEBIT, transaction.getType());
    }

    @Test
    public void givenDescription_returnsDescription() {
        final String description = makeDescription().getDescription();
        assertEquals("Rent", description);
    }

    @Test
    public void givenNoDateSet_shouldUseNowAsDefault() {
        final Date before = new Date();
        final Date transactionDate = new Transaction().getDate();
        final Date after = new Date();

        //The test-times surrounding the transaction creation should wrap the transaction date.
        //Almost all computers are so fast that the dates are going to be equal (up to millisecond precision), however
        //we cannot assume that.
        assertTrue(before.equals(transactionDate) || before.before(transactionDate));
        assertTrue(after.equals(transactionDate) || after.after(transactionDate));
    }

    @Test
    public void givenNullDate_shouldNotAffectTheDateBeingSet() {
        final Date tenSecondsAgo = Date.from(Instant.now().minus(10, ChronoUnit.SECONDS));
        final Transaction transaction = new Transaction();
        transaction.setDate(tenSecondsAgo);
        assertSame(tenSecondsAgo, transaction.getDate());

        transaction.setDate(null);
        assertSame(tenSecondsAgo, transaction.getDate()); //Null didn't change anything
    }

    private Transaction makeTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setMoney(1000);
        transaction.setType(TransactionType.CREDIT);
        return transaction;
    }

    private Transaction makeNegativeTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setMoney(-2000);
        transaction.setType(TransactionType.DEBIT);
        return transaction;
    }

    private Transaction makeDescription() {
        final Transaction transaction = new Transaction();
        transaction.setDescription("Rent");
        return transaction;
    }

    private Transaction makeNegativeTransaction2() {
        final Transaction transaction = new Transaction();
        transaction.setMoney(-2001);
        transaction.setType(TransactionType.DEBIT);
        return transaction;
    }
}