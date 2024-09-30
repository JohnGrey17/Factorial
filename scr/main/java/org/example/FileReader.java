package scr.main.java.org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader implements Runnable {

    private static final String INPUT_FILE = "input.txt";
    private final List<String> lines = new ArrayList<>();

    @Override
    public void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(INPUT_FILE));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                } else {
                    lines.add("");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + INPUT_FILE, e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public List<String> getLines() {
        return lines;
    }
}
