import bank.BankAccount;
import bank.CardAccount;
import bank.DepositAccount;

public class MainTestBankAccount {     // дублирую код для задания 6.2 (изменений не делал)
    public static void main(String[] args) {

        // тестирование работоспособности программы
        // добавлены модификаторы доступа в классы счетов


        System.out.println("\nСозданы счета с начальным балансом:");

        BankAccount bankAccount = new BankAccount(1000.10);   // создание счета

        DepositAccount depositAccount = new DepositAccount(1000.20);  // создание депозитного счета

        CardAccount cardAccount = new CardAccount(1000.30);     // создание карт-счета

        bankAccount.accountBalance();      // проверка баланса
        depositAccount.accountBalance();
        cardAccount.accountBalance();

        bankAccount.increaseAccount(-50.20);     //попытка пополнения расчетного счета отрицательным значением

        depositAccount.increaseAccount(-50.20);

        cardAccount.increaseAccount(-50.20);

        System.out.println();
        bankAccount.accountBalance();
        depositAccount.accountBalance();
        cardAccount.accountBalance();




        System.out.println("\n------------------пополнение счетов на 100, 200 и 300\n");

        bankAccount.increaseAccount(100);     //пополнение расчетного счета

        depositAccount.increaseAccount(200);  //пополнение депозитного счета

        cardAccount.increaseAccount(300);     //пополнение карт счета

        bankAccount.accountBalance();
        depositAccount.accountBalance();
        cardAccount.accountBalance();

        System.out.println("\n------------------перевод с расчетного счета на депозитный 200\n");

        bankAccount.sendAmount(depositAccount, 200);
        System.out.println();
        bankAccount.accountBalance();
        depositAccount.accountBalance();
        cardAccount.accountBalance();

        System.out.println("\n------------------перевод с депозитного счета на карт счет 500\n");

        depositAccount.sendAmount(cardAccount, 500);
        System.out.println();
        bankAccount.accountBalance();
        depositAccount.accountBalance();
        cardAccount.accountBalance();

        System.out.println("\n------------------перевод с карт-счета на расчетный счет 700 (с процентами 1%)\n");
        System.out.println();
        cardAccount.sendAmount(bankAccount, 700);

        bankAccount.accountBalance();
        depositAccount.accountBalance();
        cardAccount.accountBalance();

        System.out.println("\n------------------снятие со счетов \n");

        System.out.println(bankAccount.withdrawAccount(1700.10));
        System.out.println(depositAccount.withdrawAccount(500.10));
        System.out.println(cardAccount.withdrawAccount(500.10));


        bankAccount.accountBalance();
        depositAccount.accountBalance();
        cardAccount.accountBalance();

    }
}
