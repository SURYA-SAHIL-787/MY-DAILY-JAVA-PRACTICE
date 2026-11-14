class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Balance too low for " + Thread.currentThread().getName());
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", Remaining: " + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Account acc = new Account(1000);
        int[] withdrawals = {600, 500};

        for (int i = 0; i < withdrawals.length; i++) {
            final int amt = withdrawals[i];
            new Thread(() -> {
                try {
                    acc.withdraw(amt);
                } catch (InsufficientFundsException e) {
                    System.out.println(e.getMessage());
                }
            }, "User-" + (i + 1)).start();
        }
    }
}
