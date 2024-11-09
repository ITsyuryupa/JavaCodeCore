package Concurrency;

import java.util.concurrent.Callable;

public class ComplexTask implements Callable<Integer> {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public Integer call() {
        // Имитация выполнения задачи
        System.out.println("Выполнение задачи " + taskId + " начато.");
        this.execute();
        System.out.println("Задача " + taskId + " завершена.");
        return taskId;
    }

    private Integer execute(){
        try {
            // Имитация времени выполнения
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return taskId;
    }

}