package company;

public class Operator extends Worker {

    private double salary;

    @Override
    public void setMonthSalary(double salary) {
        this.salary = Math.round(salary);
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

}
