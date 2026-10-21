
import java.util.*;

public class BankTransactionService {

    static class Account {
        private final String accountId;
        private long balanceCents;

        public Account(String accountId, long balanceCents) {
            if (balanceCents < 0)
                throw new IllegalArgumentException("Negative balance");

            this.accountId = accountId;
            this.balanceCents = balanceCents;
        }

        public String getAccountId() {
            return accountId;
        }

        public long getBalanceCents() {
            return balanceCents;
        }

        private void deposit(long amount) {
            balanceCents = Math.addExact(balanceCents, amount);
        }

        private void withdraw(long amount) {
            if (balanceCents < amount)
                throw new IllegalStateException("Insufficient funds");

            balanceCents -= amount;
        }
    }

    private final Map<String, Account> accounts = new HashMap<>();

    public synchronized void addAccount(Account account) {
        if (accounts.containsKey(account.getAccountId()))
            throw new IllegalArgumentException("Duplicate account");

        accounts.put(account.getAccountId(), account);
    }

    public synchronized void transfer(
            String fromId, String toId, long amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid amount");

        if (fromId.equals(toId))
            throw new IllegalArgumentException("Same account");

        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);

        if (from == null || to == null)
            throw new IllegalArgumentException("Account not found");

        if (from.getBalanceCents() < amount)
            throw new IllegalStateException("Insufficient funds");

        // Check overflow before modifying either balance.
        Math.addExact(to.getBalanceCents(), amount);

        from.withdraw(amount);
        to.deposit(amount);
    }

    public static void main(String[] args) {
        BankTransactionService service =
                new BankTransactionService();

        Account alice = new Account("A101", 100000);
        Account bob = new Account("B202", 50000);

        service.addAccount(alice);
        service.addAccount(bob);

        service.transfer("A101", "B202", 25000);

        System.out.println("Alice: " + alice.getBalanceCents());
        System.out.println("Bob: " + bob.getBalanceCents());
    }
}
