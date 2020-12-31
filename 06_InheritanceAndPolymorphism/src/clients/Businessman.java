package clients;

// индивидуальный предприниматель

public class Businessman extends Individual {

    private static final int PERCENT = 100;

    private static final int HALF_PERCENT = 200;

    private static final int VALUE_OF_MONEY = 1000;


    @Override
    public void putMoney(double amount) {

        if (amount < VALUE_OF_MONEY) {

            super.putMoney(amount - amount / PERCENT);

        } else {

            super.putMoney(amount - amount / HALF_PERCENT);

        }

    }

}
