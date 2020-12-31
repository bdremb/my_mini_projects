package company;

public class TopManager extends Worker {

    private final int INCOME_STATUS = 10_000_000;
    private double topManagerPercent = 1.5;
    private double salary;

    @Override
    public void setMonthSalary(double topManagerSalary) {

        if (getCompany().getIncomeCompany() > INCOME_STATUS) {
            this.salary = Math.round(topManagerSalary + topManagerSalary * topManagerPercent);
        } else {
            this.salary = Math.round(topManagerSalary);
        }
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

}
