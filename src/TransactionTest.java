import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void givenNoDataOrMoney_returnsNoDataOrMoney() {
        final Transaction transaction = new Transaction();
        assertEquals("No data.", transaction.getSummary());
        assertEquals(0, transaction.getMoney());
        assertNull(transaction.getDescription());
        assertNull(transaction.getType());
    }

    @Test
    public void givenMoney_returnsDescriptionAndValueAndType() {
        final Transaction transaction = makeTransaction();
        assertEquals("Your account has been credited with £10.00", transaction.getSummary());
        assertEquals(1000, transaction.getMoney());
        assertEquals(TransactionType.CREDIT, transaction.getType());
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
        assertEquals("Rent", makeDescription().getDescription());
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