package company;

public class Manager extends Worker {

    private final int MIN_INCOME = 115_000;
    private final int MAX_INCOME = 140_000;
    private final int BONUS_PERSENT = 20; // 5%
    private double salary;

    @Override
    public void setMonthSalary(double salary) {
        double bonusManager = ((Math.random() * (MAX_INCOME - MIN_INCOME)) + MAX_INCOME) / BONUS_PERSENT;
        this.salary = Math.round(salary + bonusManager);
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

}
