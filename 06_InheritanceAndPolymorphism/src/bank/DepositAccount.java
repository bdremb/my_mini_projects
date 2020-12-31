package bank;

import java.util.Calendar;

public class DepositAccount extends BankAccount {

    private Calendar calendar;

    public DepositAccount(double amountOnAccount) {

        super(amountOnAccount);

    }

    @Override
    public boolean increaseAccount(double amount) {   // дублирую код для задания 6.2 (изменений не делал)

        boolean increase;

        if (increase = super.increaseAccount(amount)) {

            calendar = Calendar.getInstance();         // записываем дату внесения денег на счет

            calendar.add(Calendar.MONTH, +1);   // создаем дату , когда разрешено снимать деньги

        }

        return increase;

    }

    @Override
    public boolean withdrawAccount(double amount) {

        if (Calendar.getInstance().after(calendar)) {       // сравниваем текущую дату с разрешенной для снятия

            return super.withdrawAccount(amount);

        } else {

            System.out.print("! - Операция невозможна. Не прошел месяц после последнего внесения средств на депозитный счет. ");

            return false;
        }

    }

}