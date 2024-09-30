package scr.main.java.org.example;

import java.math.BigInteger;

public class FactorialCalculator implements Runnable{
    private final int number;
    private final ResultHandler resultHandler;

    public FactorialCalculator(int number, ResultHandler resultHandler) {
        this.number = number;
        this.resultHandler = resultHandler;
    }

    public BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(1000 / 100);
            BigInteger result = calculateFactorial(number);
            resultHandler.handleResult(number, result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Factorial calculation interrupted for number: " + number);
        }
    }
}
