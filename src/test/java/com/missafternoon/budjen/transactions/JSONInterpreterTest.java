package com.missafternoon.budjen.transactions;

import com.missafternoon.budjen.formatting.DateConverter;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JSONInterpreterTest {

    private JSONInterpreter target = new JSONInterpreter();
    private TestTransactionData testData = new TestTransactionData();

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
        String json = "[" +
                "{\"type\":\"DEBIT\",\"description\":\"Fuel\",\"money\":-2500,\"date\":\"2018-12-16\"}," +
                "{\"type\":\"CREDIT\",\"description\":\"Work\",\"money\":300000,\"date\":\"2018-11-14\"}" +
                "]";

        DateConverter dateConverter = new DateConverter();
        Transaction expectedTransaction1 = new Transaction();
        expectedTransaction1.setType(TransactionType.DEBIT);
        expectedTransaction1.setDescription("Fuel");
        expectedTransaction1.setMoney(-2500);
        expectedTransaction1.setDate(dateConverter.convert("2018-12-16"));
        Transaction expectedTransaction2 = new Transaction();
        expectedTransaction2.setType(TransactionType.CREDIT);
        expectedTransaction2.setDescription("Work");
        expectedTransaction2.setMoney(300000);
        expectedTransaction2.setDate(dateConverter.convert("2018-11-14"));
        List<Transaction> expectedTransactions = Arrays.asList(expectedTransaction1, expectedTransaction2);

        List<Transaction> transactions = target.convertToTransactions(json);

        assertEquals(expectedTransactions.size(), transactions.size());
        for (int i = 0; i < expectedTransactions.size(); i++) {
            Transaction expected = expectedTransactions.get(i);
            Transaction actual = transactions.get(i);
            assertNotNull(actual);
            assertEquals(expected.getType(), actual.getType());
            assertEquals(expected.getDescription(), actual.getDescription());
            assertEquals(expected.getMoney(), actual.getMoney());
            assertEquals(expected.getDate(), actual.getDate());
        }
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