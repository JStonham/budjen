package com.missafternoon.budjen.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFileTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void whenWriteStringUsingBufferedWriter_thenCreateFile()
            throws IOException {
        String json = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/jen/.config/budjen/transactions.json"));
        writer.write(json);
        writer.close();
    }
}
