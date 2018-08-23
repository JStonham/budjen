import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Make assertions and make runnable test methods.
//Must be public, void and have no input arguments
//Must be annotated with @Test
//test driven development
//tdd rules:
//1. RED. Write as little test code as possible to make your production code fail.
//2. GREEN. Write as little production code as possible to make your test pass.
//3. REFACTOR. Clean up the code. (Not allowed to change the behaviour of the production code).

public class PurseTest {
    public static final long BIG_NUMBER = 64000000000l;
    private Purse target = new Purse();

    @Test
    public void hasZeroBalanceByDefault() {
        assertEquals(0, target.getBalance());
    }

    @Test
    public void canHandleMultipleTransactions() {
        target.addTransaction(makeTransaction(128));
        target.addTransaction(makeTransaction(-256));
        target.addTransaction(makeTransaction(64));
        assertEquals(-64, target.getBalance());
    }

    @Test
    public void canHandleVeryLargeAmountsOfMoney() {
        target.addTransaction(makeTransaction(BIG_NUMBER));
        assertEquals(BIG_NUMBER, target.getBalance());
    }

    private Transaction makeTransaction(long money) {
        Transaction transaction = new Transaction();
        transaction.setMoney(money);
        return transaction;
    }
}