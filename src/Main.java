import Collection.ArrayFilter;
import Collection.ElementCounter;
import Collection.Filter;
import Collection.SquareFilter;

import Concurrency.BlockingQueue;
import MyStringBuilder.MyStringBuilder;
import MyStringBuilder.MementoStorage;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

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


