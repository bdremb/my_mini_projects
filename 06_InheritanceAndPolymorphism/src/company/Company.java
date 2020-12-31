package company;

import java.util.*;

public class Company {

    private final double DEFAULT_VALUE = 50_000;
    private final double RAND_VALUE = 10_000;
    private final List<Employee> employeeListMain = new ArrayList<>();
    private final Map<Class, Double> salarys;
    private final int incomeCompany;

    public Company(int incomeCompany, Map<Class, Double> EmployeeSalary) {
        salarys = new HashMap<>(EmployeeSalary);
        this.incomeCompany = incomeCompany;
    }

    public List<Employee> getEmployeeListMain() {
        return employeeListMain;
    }


    public Map<Class, Double> getSalarys() {
        return salarys;
    }

    public int getIncomeCompany() {
        return incomeCompany;
    }

    public void hire(Employee employee) {                         // найм сотрудника
        if (!employeeListMain.contains(employee)) {
            employee.setCompany(this);
            employee.setMonthSalary(new Random().nextDouble() * RAND_VALUE
                    + salarys.getOrDefault(employee.getClass(), DEFAULT_VALUE));
            employeeListMain.add(employee);
        } else {
            System.out.println("Такой сотрудник уже работает");
        }
    }

    public void hireAll(List<Employee> employeeList) {              // найм списка сотрудников
        for (Employee employee : employeeList) {
            hire(employee);
        }

    }

    public void fire(Employee employee) {                             // увольнение сотрудника
        if (employeeListMain.contains(employee)) {
            employee.setMonthSalary(0);
            employee.setCompany(null);
            employeeListMain.remove(employee);
        } else {
            System.out.println("Такой сотрудник не работает");
        }
    }

    public List<Employee> getTopSalaryStaff(int count) {
        sortList(employeeListMain);
        List<Employee> listSort = exclusiveList(employeeListMain);
        Collections.reverse(listSort);
        return new ArrayList<>(listSort).subList(0, Math.min(count, listSort.size()));
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        sortList(employeeListMain);
        List<Employee> listSort = exclusiveList(employeeListMain);
        return new ArrayList<>(listSort).subList(0, Math.min(count, listSort.size()));
    }

    private List<Employee> exclusiveList(List<Employee> mainList) {
        List<Employee> listForSort = new ArrayList<>();
        listForSort.add(mainList.get(0));
        for (int i = 1; i < mainList.size(); i++) {
            if ((mainList.get(i - 1).getMonthSalary() != mainList.get(i).getMonthSalary())) {
                listForSort.add(mainList.get(i));
            }
        }
        return listForSort;
    }

    private void sortList(List<Employee> list) {
        list.sort(Comparator.comparingDouble(Employee::getMonthSalary));
    }

}
