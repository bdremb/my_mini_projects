package clients;

import java.text.DecimalFormat;

public abstract class Client {

    private double amountOfMoney;


    protected double getAmountOfMoney() {

        return amountOfMoney;

    }


    protected void setAmountOfMoney(double amountOfMoney) {

        this.amountOfMoney = amountOfMoney;

    }


    public abstract void putMoney(double amount);


    public abstract void withdrawMoney(double amount);


    public void accauntBalance() {

        System.out.println("Остаток на счете " + getClass().toString().substring(14) + ":  "

                + new DecimalFormat("0.00").format(amountOfMoney));

    }

}
