package clients;

// физическое лицо

import java.text.DecimalFormat;

public class Individual extends Client {


    @Override
    public void putMoney(double amount) {

        if (amount > 0 && amount < Double.MAX_VALUE) {

            setAmountOfMoney(getAmountOfMoney() + amount);

            System.out.println(getClass().toString().substring(14) + ": Сумма: "

                    + new DecimalFormat("0.00").format(amount) + " успешно добавлена на счет. ");

        } else {

            System.out.println("! - Указано недопустимое значение " + getClass().toString().substring(14));

        }

    }


    @Override
    public void withdrawMoney(double amount) {

        if (amount > 0 && amount <= getAmountOfMoney()) {

            setAmountOfMoney(getAmountOfMoney() - amount);

            System.out.println(getClass().toString().substring(14) + ": Сумма: "

                    + new DecimalFormat("0.00").format(amount) + " успешно снята со счета. ");


        } else {

            System.out.println("! - Указана слишком большая сумма денег " + getClass().toString().substring(14));

        }

    }

}
