package scr.main.java.org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter implements Runnable {

    private final List<String> results;
    private static final String OUTPUT_FILE = "output.txt";

    public FileWriter(List<String> results) {
        this.results = results;
    }

    @Override
    public void run() {
        File file = new File(OUTPUT_FILE);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new java.io.FileWriter(file))) {
            for (String result : results) {
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file.getName(), e);
        }
    }
}
