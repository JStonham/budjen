package com.missafternoon.budjen.transactions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

class JSONInterpreter {

    private final ObjectMapper mapper = new ObjectMapper();

    String convertToString(final List<Transaction> transactions) {
        try {
            return mapper.writeValueAsString(transactions);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<Transaction> convertToTransactions(final String string) {
        try {
            return mapper.readValue(string, new TypeReference<List<Transaction>>() {
            });
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}