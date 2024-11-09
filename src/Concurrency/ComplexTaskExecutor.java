package Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComplexTaskExecutor {
    private final int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public int executeTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("Все задачи завершены. Выполняем объединение результатов.");
        });

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i + 1;
            Callable<Integer> task = () -> {
                ComplexTask resultTask = new ComplexTask(taskId);
                Integer result = resultTask.call();
                barrier.await(); // Ожидание завершения всех задач
                return result;
            };
            futures.add(executorService.submit(task));
        }

        executorService.shutdown();

        int totalResult = 0;
        for (Future<Integer> future : futures) {
            try {
                totalResult += future.get(); // Суммируем результаты
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return totalResult;
    }
}