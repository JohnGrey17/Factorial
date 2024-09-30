package scr.main.java.org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ResultHandler {


    private final ConcurrentHashMap<Integer, BigInteger> results = new ConcurrentHashMap<>();

    public void handleResult(int number, BigInteger result) {
        results.put(number, result);
    }

    public ConcurrentHashMap<Integer, BigInteger> getResults() {
        return results;
    }

    public List<String> formatResults(List<String> lines) {
        List<String> formattedResults = new ArrayList<>(lines);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (!line.trim().isEmpty()) {
                try {
                    int number = Integer.parseInt(line);
                    BigInteger result = results.get(number);
                    if (result != null) {
                        formattedResults.set(i, number + " = " + result);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Can't find factorial for line: "
                            + line + " because it is not a number");
                }
            }
        }
        return formattedResults;
    }
}