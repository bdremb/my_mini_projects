import main.Account;
import main.Bank;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTest {
    private static Bank bank;
    private static long amount;
    private static long beforeSum;

    @BeforeClass
    public static void setUp() {
        bank = new Bank();
        ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            accounts.put(i + "", new Account(i + ""));
        }
        bank.setAccounts(accounts);
        beforeSum = getSum(bank);
    }

    @Test
    public void testTransfer() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        ExecutorService executor2 = Executors.newFixedThreadPool(100);
        Random random = new Random();

        for (int i = 0; i < bank.getAccounts().size() - 10; i++) {
            String a = i + "";
            String b = i + "";
            String c = i + 3 + "";
            String d = i + 9 + "";

            executor.submit(new Thread(() -> {
                amount = (long) (Math.random() * 5150) + 44900;
                bank.transfer(bank.getAccounts().get(a).getAccNumber(), bank.getAccounts().get(b).getAccNumber(), amount);
            }));
            executor2.submit(new Thread(() -> {
                amount = random.nextInt(50500);
                bank.transfer(bank.getAccounts().get(c).getAccNumber(), bank.getAccounts().get(d).getAccNumber(), amount);
            }));
        }

        executor.shutdown();
        executor2.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
            Assert.assertEquals(beforeSum, getSum(bank));
            executor2.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(beforeSum, getSum(bank));
    }

    @Test
    public void getBalance() {
        long actual = bank.getBalance("1");
        long actual2 = bank.getBalance("2");
        long expected = 500000;
        long expected2 = 500000;
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected2, actual2);
    }

    private static long getSum(Bank bank) {
        long sum = 0;
        for (String key : bank.getAccounts().keySet()) {
            sum += bank.getAccounts().get(key).getMoney();
        }
        return sum;
    }

}
