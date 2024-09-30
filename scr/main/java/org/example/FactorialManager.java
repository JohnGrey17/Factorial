package scr.main.java.org.example;

import java.util.List;
import java.util.Scanner;

public class FactorialManager {

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Thread readerThread = new Thread(fileReader);
        readerThread.start();

        try {
            readerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't read file");
        }

        List<String> lines = fileReader.getLines();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert number of threads: ");
        int numberOfThreads = scanner.nextInt();

        TaskExecutor taskExecutor = new TaskExecutor(numberOfThreads, lines);
        List<String> formattedResults = taskExecutor.executeTasks();

        Thread writerThread = new Thread(new FileWriter(formattedResults));
        writerThread.start();

        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Please check all Factorial numbers that are in output.txt file");
    }
}
