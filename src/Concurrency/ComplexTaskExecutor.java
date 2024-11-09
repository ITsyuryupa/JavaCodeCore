package Concurrency;

import Concurrency.ComplexTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    List<Integer> results;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public synchronized void executeTasks() {
        results = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks + 1, () -> {
            System.out.println("Все задачи завершены. Выполняем объединение результатов.");
            System.out.println(results.stream()
                    .mapToInt(Integer::intValue)
                    .sum());
        });

        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i + 1;
            Runnable task = () -> {
                ComplexTask resultTask = new ComplexTask(taskId);
                Integer result = resultTask.call();
                results.add(result);
                try {
                    barrier.await(); // Ожидание завершения всех задач
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            };
            executorService.submit(task);
        }

        executorService.shutdown();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }
}