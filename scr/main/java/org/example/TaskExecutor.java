package scr.main.java.org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {

    private final int numberOfThreads;
    private final List<String> lines;

    public TaskExecutor(int numberOfThreads, List<String> lines) {
        this.numberOfThreads = numberOfThreads;
        this.lines = lines;
    }

    public List<String> executeTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        ResultHandler resultHandler = new ResultHandler();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            try {
                int number = Integer.parseInt(line);
                executorService.submit(new FactorialCalculator(number, resultHandler));
            } catch (NumberFormatException e) {

            }
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }

        return resultHandler.formatResults(lines);
    }
}
