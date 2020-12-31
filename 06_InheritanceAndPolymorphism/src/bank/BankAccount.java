package bank;

import java.text.DecimalFormat;

public class BankAccount {  // дублирую код для задания 6.2 (изменений не делал)

    private double amountOnAccount;


    public BankAccount(double amountOnAccount) {

        this.amountOnAccount = amountOnAccount;

    }

    public boolean increaseAccount(double amount) {

        if (amount > 0) {

            amountOnAccount += amount;

            return true;

        } else {

            return false;

        }

    }

    public boolean withdrawAccount(double amount) {

        if (amount > 0 && amount <= amountOnAccount) {

            amountOnAccount -= amount;

            return true;

        } else {

            System.out.print("! - Проверьте правильность вводимого значения. " + getClass().toString().substring(11) + " ");

            return false;

        }
    }

    public void accountBalance() {

        System.out.println("Остаток на " + getClass().toString().substring(11) + " - "

                + new DecimalFormat("0.00").format(amountOnAccount));

    }

    public boolean sendAmount(BankAccount receiver, double amount) {

        boolean send;

        if (withdrawAccount(amount)) {             // если деньги сняли, то переводим на указанный счет

            receiver.increaseAccount(amount);

            send = true;            // если деньги перевели, то присваиваем send = true;

        } else {

            send = false;           // если деньги не перевели, присваиваем false
        }

        System.out.print(getClass().toString().substring(11) + ". Send status: " + send + ".\n");

        return send;

    }

}
