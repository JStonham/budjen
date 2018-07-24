import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Make assertions and make runnable test methods.
//Must be public, void and have no input arguments
//Must be annotated with @Test
//test driven development
public class BankTest {
    Bank bank = new Bank();//<Type> <variable name> = new <Constructor call>;

    @Test
    public void testZero() {
        Transaction transaction = makeTransaction(0);
        assertEquals(0, bank.add(transaction));
    }

    @Test
    public void testFive() {
        long balance = bank.add(makeTransaction(5));
        assertEquals(5, balance);
    }

    @Test
    public void testTransaction() {
        Transaction transaction1 = makeTransaction(10);
        assertEquals(10, bank.add(transaction1));
        Transaction transaction2 = makeTransaction(-8);
        assertEquals(2, bank.add(transaction2));
        Transaction transaction3 = makeTransaction(4);
        assertEquals(6, bank.add(transaction3));
    }

    private Transaction makeTransaction(long money) {
        Transaction transaction = new Transaction();
        transaction.setMoney(money);
        return transaction;
    }

}