package StreamAPI.Task3;


import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        long number = 20;  // Пример числа для вычисления факториала
        FactorialTask task = new FactorialTask(number);

        // Запуск вычислений с использованием ForkJoinPool
        BigInteger result = task.fork().join();

        System.out.println("Factorial of " + number + " is " + result);
    }
}
