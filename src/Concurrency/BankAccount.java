package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final int accountNumber;
    private final AtomicInteger balance;

    private final Lock lock = new ReentrantLock();

    public BankAccount(int accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = new AtomicInteger(initialBalance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance.get();
    }

    public Lock getLock() {
        return lock;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance.addAndGet(amount);
        }
    }

    public boolean withdraw(int amount) {
        //пытаемся списать, если вдруг значение изменилось, начинаем сначала
        while (true) {
            int currentBalance = balance.get();
            if (currentBalance < amount) {
                return false;
            }
            int newBalance = currentBalance - amount;
            // Атомарное обновление баланса, если текущий баланс не изменился
            if (balance.compareAndSet(currentBalance, newBalance)) {
                return true;
            }
        }
    }
}
