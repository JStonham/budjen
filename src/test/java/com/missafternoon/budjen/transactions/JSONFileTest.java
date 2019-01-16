package com.missafternoon.budjen.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class JSONFileTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private BufferedWriter writer;

    @Test
    public void whenWriteStringUsingBufferedWriter_thenCreateFile() {
        String home = System.getProperty("user.home");
        String json = "{}";
        try {
            writer = new BufferedWriter(new FileWriter(home + "/.config/budjen/transactions.json"));
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void givenUsingJava7_whenWritingToFile_thenCorrect()
            throws IOException {
        String home = System.getProperty("user.home");
        String str = "{}";

        Path path = Paths.get(home + "/.config/budjen/transactions.json");
        byte[] strToBytes = str.getBytes();

        Files.write(path, strToBytes);

        String read = Files.readAllLines(path).get(0);
        assertEquals(str, read);
    }
}
