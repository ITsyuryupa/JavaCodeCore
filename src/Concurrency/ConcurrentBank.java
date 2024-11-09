package Concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    private final ConcurrentHashMap<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicInteger accountNumberGenerator = new AtomicInteger(0);

    public BankAccount createAccount(int initialBalance) {
        int accountNumber = accountNumberGenerator.getAndIncrement();
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, int amount) {

        int fromAccountNumber = fromAccount.getAccountNumber();
        int toAccountNumber = toAccount.getAccountNumber();

        if (fromAccount == null || toAccount == null) {
            return false;
        }
        else if (fromAccountNumber == toAccountNumber || amount <= 0) {
            return false;
        } else if (fromAccount.getBalance() < amount) {
            return false;
        }


        // избегание deadlock
        BankAccount firstLock = fromAccountNumber < toAccountNumber ? fromAccount : toAccount;
        BankAccount secondLock = fromAccountNumber < toAccountNumber ? toAccount : fromAccount;

        firstLock.getLock().lock();
        secondLock.getLock().lock();
        try {
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                return true;
            }
            return false;
        } finally {
            secondLock.getLock().unlock();
            firstLock.getLock().unlock();
        }
    }

    public int getTotalBalance() {
        int totalBalance = 0;
        for (BankAccount account : accounts.values()) {
            account.getLock().lock();
            try {
                totalBalance += account.getBalance();
            } finally {
                account.getLock().unlock();
            }
        }
        return totalBalance;
    }
}
