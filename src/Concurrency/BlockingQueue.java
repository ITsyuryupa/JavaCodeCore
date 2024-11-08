package Concurrency;

public class BlockingQueue<T> {
    private final T[] queue;
    private int size;        // Текущий размер очереди
    private int head;        // Указатель на начало очереди
    private int tail;
    private final int capacity; // Максимальный размер очереди

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    //add
    public synchronized void enqueue(T item) throws InterruptedException {
        while (size == capacity) {
            wait();
        }
        queue[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        T item = queue[head];
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return item;
    }

    // Метод для получения текущего размера очереди
    public synchronized int size() {
        return size;
    }
}

