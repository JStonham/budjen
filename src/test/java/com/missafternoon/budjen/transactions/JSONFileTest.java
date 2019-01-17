package com.missafternoon.budjen.transactions;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFileTest {

    @Test
    public void whenWriteStringUsingBufferedWriter_thenCreateFile() {
        final String dir = System.getProperty("user.home") + "/.test/budjen/";
        new File(dir).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "transactions.json"))) {
            writer.write("{}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
