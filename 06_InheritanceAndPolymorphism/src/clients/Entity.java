package clients;

// юридическое лицо

public class Entity extends Individual {

    @Override
    public void withdrawMoney(double amount) {

        super.withdrawMoney(amount + amount / 100);

    }

}
