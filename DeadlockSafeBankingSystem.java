import java.util.*;
import java.util.concurrent.locks.*;

class InvalidAccountException extends Exception {
    InvalidAccountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

class Account {
    private final int id;
    private int balance;
    private final ReentrantLock lock;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void withdraw(int amount) throws InsufficientBalanceException {
        if (balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account " + id);
        }

        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}

class Bank {
    private final Map<Integer, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public void transfer(int fromId, int toId, int amount)
            throws InvalidAccountException, InsufficientBalanceException {

        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);

        if (from == null || to == null) {
            throw new InvalidAccountException("Invalid account id");
        }

        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        first.getLock().lock();
        second.getLock().lock();

        try {
            from.withdraw(amount);
            to.deposit(amount);

            System.out.println("Transferred " + amount + " from " + fromId + " to " + toId);
        } catch (InsufficientBalanceException e) {
            System.out.println("Rollback: " + e.getMessage());
            throw e;
        } finally {
            second.getLock().unlock();
            first.getLock().unlock();
        }
    }

    public void printBalances() {
        for (Account account : accounts.values()) {
            System.out.println("Account " + account.getId() + ": " + account.getBalance());
        }
    }
}

public class DeadlockSafeBankingSystem {
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();

        bank.addAccount(new Account(1, 1000));
        bank.addAccount(new Account(2, 1000));
        bank.addAccount(new Account(3, 1000));

        Runnable r1 = () -> {
            try {
                bank.transfer(1, 2, 300);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable r2 = () -> {
            try {
                bank.transfer(2, 1, 500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable r3 = () -> {
            try {
                bank.transfer(3, 1, 2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        bank.printBalances();
    }
}
