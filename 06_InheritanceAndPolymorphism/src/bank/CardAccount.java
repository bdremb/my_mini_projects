package bank;

public class CardAccount extends BankAccount {   // дублирую код для задания 6.2 (изменений не делал)

    public CardAccount(double amountOnAccount) {

        super(amountOnAccount);

    }

    @Override
    public boolean withdrawAccount(double amount) {

        return super.withdrawAccount(amount + amount / 100);

    }

}
