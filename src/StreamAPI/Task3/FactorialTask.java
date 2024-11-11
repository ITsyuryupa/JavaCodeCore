package StreamAPI.Task3;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {
    private final long number;

    // Конструктор, принимающий число для вычисления факториала
    public FactorialTask(long number) {
        this.number = number;
    }

    // Метод для вычисления факториала с использованием рекурсии
    @Override
    protected BigInteger compute() {
        if (number <= 1) {
            return BigInteger.ONE;  // Базовый случай: факториал 0 или 1 равен 1
        } else {
            // Создаем подзадачу для вычисления факториала числа number - 1
            FactorialTask subTask = new FactorialTask(number - 1);
            // Запускаем подзадачу
            subTask.fork();
            // Получаем результат подзадачи и умножаем на текущее число
            return BigInteger.valueOf(number).multiply(subTask.join());
        }
    }
}

