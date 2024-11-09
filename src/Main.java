import Collection.ArrayFilter;
import Collection.ElementCounter;
import Collection.Filter;
import Collection.SquareFilter;

import Concurrency.BankAccount;
import Concurrency.BlockingQueue;
import Concurrency.ConcurrentBank;
import MyStringBuilder.MyStringBuilder;
import MyStringBuilder.MementoStorage;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ConcurrentBank bank = new ConcurrentBank();

        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);
        BankAccount account3 = bank.createAccount(800);
        BankAccount account4 = bank.createAccount(1200);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable transferTask1 = () -> bank.transfer(account1, account2, 200);
        Runnable transferTask2 = () -> bank.transfer(account2, account3, 150);
        Runnable transferTask3 = () -> bank.transfer(account3, account4, 300);
        Runnable transferTask4 = () -> bank.transfer(account4, account1, 400);
        Runnable transferTask5 = () -> bank.transfer(account1, account3, 250);
        Runnable transferTask6 = () -> bank.transfer(account2, account4, 100);
        Runnable transferTask7 = () -> bank.transfer(account3, account2, 50);

        executorService.submit(transferTask1);
        executorService.submit(transferTask2);
        executorService.submit(transferTask3);
        executorService.submit(transferTask4);
        executorService.submit(transferTask5);
        executorService.submit(transferTask6);
        executorService.submit(transferTask7);

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Не все задачи завершены в отведенное время.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total balance: " + bank.getTotalBalance());
        System.out.println("Balance of account 1: " + account1.getBalance());
        System.out.println("Balance of account 2: " + account2.getBalance());
        System.out.println("Balance of account 3: " + account3.getBalance());
        System.out.println("Balance of account 4: " + account4.getBalance());

    }
}


class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Producing " + i);
                queue.enqueue(i);
                Thread.sleep(500); // Имитируем время производства
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int item = queue.dequeue();
                System.out.println("Consuming " + item);
                Thread.sleep(1000); // Имитируем время потребления
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


