package com.missafternoon.budjen.transactions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JSONInterpreter {

    private ObjectMapper mapper;

    public JSONInterpreter() {
        mapper = new ObjectMapper();
    }

    public String convertToString(List<Transaction> transactions) {
        try {
            return mapper.writeValueAsString(transactions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> convertToTransactions(String string) {
        try {
            return mapper.readValue(string, new TypeReference<List<Transaction>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}