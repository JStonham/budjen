import com.sun.corba.se.spi.transport.TransportDefault;
import org.junit.Test;

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
}