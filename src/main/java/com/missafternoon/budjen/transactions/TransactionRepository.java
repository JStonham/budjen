package com.missafternoon.budjen.transactions;

import java.io.*;

public class TransactionRepository {

    private final String directory = System.getProperty("user.home") + "/.main/budjen/";
    private final String filename = "transactions.json";

    public String findStoredJson() {
        File file = new File(directory + filename);
        boolean fileExists = file.exists();
        if (!fileExists) {
            file.mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("{}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return readJson(file);
    }

    private String readJson(final File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            final StringBuilder fileContentsBuilder = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                fileContentsBuilder.append(line);
            }
            br.close();
            return fileContentsBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}