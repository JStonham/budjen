package com.missafternoon.budjen.transactions;

import com.missafternoon.budjen.formatting.DateConverter;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JSONInterpreterTest {

    private JSONInterpreter target = new JSONInterpreter();
    private TestTransactionData testData = new TestTransactionData();
    private DateConverter dateConverter = new DateConverter();

    @Test
    public void writeJavaObjectIntoJSONString() throws JSONException {
        String json = target.convertToString(testData.getTransactionData());
        String expectedJson = "[" +
                "{\"type\":\"DEBIT\",\"description\":\"Plastic Surgery\",\"money\":-2500000,\"date\":\"2018-06-13\"}," +
                "{\"type\":\"CREDIT\",\"description\":\"Let flat\",\"money\":420000,\"date\":\"2018-06-08\"}" +
                "]";

        JSONAssert.assertEquals(expectedJson, json, JSONCompareMode.STRICT);
    }

    @Test
    public void readJSONStringIntoJavaObject() {
        final String json = "[" +
                "{\"type\":\"DEBIT\",\"description\":\"Fuel\",\"money\":-2500,\"date\":\"2018-12-16\"}," +
                "{\"type\":\"CREDIT\",\"description\":\"Work\",\"money\":300000,\"date\":\"2018-11-14\"}" +
                "]";

        final List<Transaction> transactions = target.convertToTransactions(json);
        final List<Transaction> expectedTransactions = asList(firstTransaction(), secondTransaction());

        assertEquals(expectedTransactions.size(), transactions.size());
        for (int i = 0; i < expectedTransactions.size(); i++) {
            assertSameProperties(expectedTransactions.get(i), transactions.get(i));
        }
    }

    private void assertSameProperties(final Transaction expected, final Transaction actual) {
        assertNotNull(actual);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getMoney(), actual.getMoney());
        assertEquals(expected.getDate(), actual.getDate());
    }

    private Transaction firstTransaction() {
        final Transaction firstTransaction = new Transaction();
        firstTransaction.setType(TransactionType.DEBIT);
        firstTransaction.setDescription("Fuel");
        firstTransaction.setMoney(-2500);
        firstTransaction.setDate(dateConverter.convert("2018-12-16"));
        return firstTransaction;
    }

    private Transaction secondTransaction() {
        final Transaction secondTransaction = new Transaction();
        secondTransaction.setType(TransactionType.CREDIT);
        secondTransaction.setDescription("Work");
        secondTransaction.setMoney(300000);
        secondTransaction.setDate(dateConverter.convert("2018-11-14"));
        return secondTransaction;
    }

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
}