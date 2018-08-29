import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void givenNoDataOrMoney_returnsNoDataOrMoney() {
        Transaction transaction = new Transaction();
        String summary = transaction.getSummary();
        long money = transaction.getMoney();
        String description = transaction.getDescription();
        TransactionType type = transaction.getType();
        assertEquals("No data.", summary);
        assertEquals(0, money);
        assertNull(description);
        assertNull(type);
    }

    @Test
    public void givenMoney_returnsDescriptionAndValue() {
        String summary = makeTransaction().getSummary();
        long money = makeTransaction().getMoney();
        assertEquals("Your account has been credited with £10.00", summary);
        assertEquals(1000, money);
    }

    @Test
    public void givenRoundNegativeMoney_returnsRoundDetails() {
        Transaction transaction = makeNegativeTransaction();
        assertEquals("You have spent £20.00", transaction.getSummary());
        assertEquals(-2000, transaction.getMoney());
    }

    @Test
    public void givenDecimalNegativeMoney_returnsDecimalDetails() {
        Transaction transaction = makeNegativeTransaction2();
        assertEquals("You have spent £20.01", transaction.getSummary());
        assertEquals(-2001, transaction.getMoney());
    }

    @Test
    public void givenDescription_returnsDescription() {
        String description = makeDescription().getDescription();
        assertEquals("Rent",description);
    }

    @Test
    public void givenNoDateSet_shouldUseNowAsDefault() {
        Date before = new Date();
        Date transactionDate = new Transaction().getDate();
        Date after = new Date();

        //The test-times surrounding the transaction creation should wrap the transaction date.
        //Almost all computers are so fast that the dates are going to be equal (up to millisecond precision), however
        //we cannot assume that.
        assertTrue(before.equals(transactionDate) || before.before(transactionDate));
        assertTrue(after.equals(transactionDate) || after.after(transactionDate));
    }

    @Test
    public void givenNullDate_shouldNotAffectTheDateBeingSet() {
        Date tenSecondsAgo = Date.from(Instant.now().minus(10, ChronoUnit.SECONDS));
        Transaction transaction = new Transaction();
        transaction.setDate(tenSecondsAgo);
        assertSame(tenSecondsAgo, transaction.getDate());

        transaction.setDate(null);
        assertSame(tenSecondsAgo, transaction.getDate()); //Null didn't change anything
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