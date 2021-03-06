package com.missafternoon.budjen;

import com.missafternoon.budjen.formatting.DateConverter;
import com.missafternoon.budjen.print.Logger;
import com.missafternoon.budjen.transactions.Transaction;
import com.missafternoon.budjen.transactions.TransactionData;
import com.missafternoon.budjen.transactions.TransactionType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.missafternoon.budjen.Application.HELP_MESSAGE;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    private TestTransactionData testData = new TestTransactionData();
    private TestLogger testLogger = new TestLogger();

    private Application target = new Application(testData, testLogger);

    @Test
    public void givenPrintAsInputArgument_PrintTransactions() {
        target.start(new String[]{"print"});
        final List<String> messages = testLogger.getPrintedMessages();
        assertEquals(2, messages.size());
        assertEquals("CREDIT, Let flat, £4200.00, 2018-06-08", messages.get(0));
        assertEquals("DEBIT, Plastic Surgery, £25000.00, 2018-06-13", messages.get(1));
    }

    @Test
    public void testExpectedOutputGetsPrinted() {
        target.start(new String[]{""});
        expectSingleLogCall("budjen '' is not a budjen command. See 'budjen help'.");
    }

    @Test
    public void givenHelpAsInputArgument_PrintHelpMessage() {
        target.start(new String[]{"help"});
        expectSingleLogCall(HELP_MESSAGE);
    }

    @Test
    public void givenRubbishAsInputArgument_PrintHelpMessage() {
        target.start(new String[]{"dkjfhkjresh"});
        expectSingleLogCall("budjen 'dkjfhkjresh' is not a budjen command. See 'budjen help'.");
    }

    @Test
    public void givenNothingAsInputArgument_PrintHelpMessage() {
        target.start(new String[]{});
        expectSingleLogCall(HELP_MESSAGE);
    }

    @Test
    public void givenGarbageForAddCommand_tellMeValidationFailed() {
        target.start(new String[]{"add", "ufdshglieahgliurea", "sglreaglehrg"});
        expectSingleLogCall("Invalid arguments given to add command.");
    }

    @Test
    public void givenCorrectInputForAddCommand_addToTransactionData() {
        int size = testData.getTransactionData().size();
        target.start(new String[]{"add", "--debit", "--description", "fish", "--amount", "500"});
        int newSize = testData.getTransactionData().size();
        assertEquals(size + 1, newSize);
    }

    private void expectSingleLogCall(String helpMessage) {
        final List<String> messages = testLogger.getPrintedMessages();
        assertEquals(1, messages.size());
        assertEquals(helpMessage, messages.get(0));
    }

    /**
     * This class replaces the test data we use when we run the application
     * normally with something specific to this test.
     * In particular, the data contains both credit and debit, and is unordered.
     */
    private class TestTransactionData implements TransactionData {
        private final DateConverter dateConverter = new DateConverter();
        private final List<Transaction> list = new ArrayList<>();

        TestTransactionData() {
            list.add(tomorrow());
            list.add(yesterday());
        }

        @Override
        public List<Transaction> getTransactionData() {
            return list;
        }

        private Transaction yesterday() {
            final Transaction transaction = new Transaction();
            transaction.setType(TransactionType.CREDIT);
            transaction.setDescription("Let flat");
            transaction.setMoney(420_000L);
            transaction.setDate(dateConverter.convert("2018-06-08"));
            return transaction;
        }

        private Transaction tomorrow() {
            final Transaction transaction = new Transaction();
            transaction.setType(TransactionType.DEBIT);
            transaction.setDescription("Plastic Surgery");
            transaction.setMoney(-2_500_000L);
            transaction.setDate(dateConverter.convert("2018-06-13"));
            return transaction;
        }
    }

    /**
     * This class replaces the default logger, and instead of printing to the
     * screen (no point in that for a test) it stores all received messages
     * in order. These messages are then retrievable through getPrintedMessages.
     */
    private class TestLogger implements Logger {
        private List<String> printedMessages = new ArrayList<>();

        @Override
        public void print(String message) {
            printedMessages.add(message);
        }

        List<String> getPrintedMessages() {
            return printedMessages;
        }
    }
}
