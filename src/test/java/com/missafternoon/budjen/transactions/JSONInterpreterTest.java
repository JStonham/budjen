package com.missafternoon.budjen.transactions;

import com.missafternoon.budjen.formatting.DateConverter;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        String string = "CREDIT, Work, £3000.00, 2018-11-14, DEBIT, Fuel, £25.00, 2018-12-16";
        List<Transaction> transactions = target.convertToTransactions(string);
        assertEquals(2, transactions.size());
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