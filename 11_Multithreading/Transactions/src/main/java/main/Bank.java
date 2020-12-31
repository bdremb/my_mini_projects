package main;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private ConcurrentHashMap<String, Account> accounts;
    private Set<Account> blockedAccounts;
    private final Random random = new Random();
    private static final long EXCESS_AMOUNT = 50000;

    public Bank() {
        blockedAccounts = Collections.synchronizedSet(new HashSet<>());
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) {
        System.out.println(" *** start isFraud ***  from " +
                fromAccountNum + " to " +
                toAccountNum + " amount = " + amount);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account accountFrom = getAccounts().get(fromAccountNum);
        Account accountTo = getAccounts().get(toAccountNum);

        boolean lock = Long.parseLong(fromAccountNum) < Long.parseLong(toAccountNum);

        synchronized (lock ? accountFrom : accountTo) {
            synchronized (lock ? accountTo : accountFrom) {
                if (fromAccountNum.equals(toAccountNum)) {
                    System.err.println("Выполнение операции невозможно");
                    return;
                }

                long from = accountFrom.getMoney();
                long to = accountTo.getMoney();
                if (amount > EXCESS_AMOUNT && isFraud(fromAccountNum, toAccountNum, amount)) {
                    getBlockedAccounts().add(accountTo);
                    getBlockedAccounts().add(accountFrom);
                    System.out.println(" --------------------------------------------------------\n" +
                            " | Транзакция " + fromAccountNum +
                            " to " + toAccountNum +
                            "  заблокирована amount=" + amount + "\t|\n" +
                            " --------------------------------------------------------");
                } else if (!getBlockedAccounts().contains(accountTo) && !getBlockedAccounts().contains(accountFrom)) {
                    if (from >= amount) {
                        accountFrom.setMoney(from - amount);
                        accountTo.setMoney(to + amount);
                        System.out.println("\nТранзакция успешно выполнена.\n" +
                                "Переведено " + amount + "руб " + " " + fromAccountNum + " $ " + from + ", " + toAccountNum + " $ " + to + "\n" +
                                "balance from = " + fromAccountNum + "  " + getBalance(fromAccountNum) +
                                " balance to = " + toAccountNum + "  " + getBalance(toAccountNum) + "\n" +
                                (getBalance(fromAccountNum) + getBalance(toAccountNum)) + " == " + (from + to) + "\n");
                    } else {
                        System.out.println("Недостаточно средств на счете");
                    }
                } else {
                    System.out.println("Операция невозможна, счета заблокированы");
                }
            }
        }
    }


    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ConcurrentHashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Account> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(Set<Account> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }
}
