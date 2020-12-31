import company.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTestCompany {


    public static void main(String[] args) {


        /**
         * Первая компания
         */
        Map<Class, Double> salars = new HashMap<>();

        salars.put(Manager.class, 100000.00);
        salars.put(TopManager.class, 140000.0);
        salars.put(Operator.class, 80000.00);

        Company company1 = new Company(12000000, salars);


        /**
         * Найм сотрудников в первую компанию
         */


        Employee man1 = new Manager();
        man1.setCompany(company1);
        company1.hire(man1);

        Employee man2 = new TopManager();
        man2.setCompany(company1);
        company1.hire(man2);

        for (int i = 0; i < 180; i++) {
            company1.hire(new Operator());

            if (i >= 170) {
                company1.hire(new TopManager());
            }

            if (i >= 100) {
                company1.hire(new Manager());
            }
        }


        List<Employee> lowStaff = company1.getLowestSalaryStaff(15);
        List<Employee> topStaff = company1.getTopSalaryStaff(15);

        for (Employee employee : topStaff) {
            System.out.println(employee.getMonthSalary() + " company1   top");
        }

        System.out.println();

        for (Employee employee : lowStaff) {
            System.out.println(employee.getMonthSalary() + " company1  low");
        }

        System.out.println();

        List<Employee> fireList = company1.getEmployeeListMain();
        System.out.println(fireList.size() + " - количество сотрудников в первой компании\n");

        for (int i = 0; i < 100; i = i + 3) {  // увольнение каждого 3-го сотрудника из 1-й компании
            company1.fire(fireList.get(i));

        }

        System.out.println(fireList.size() + " - количество сотрудников в первой компании после увольнения\n");

        List<Employee> lowStaffAfterFire = company1.getLowestSalaryStaff(15);
        for (Employee employee : lowStaffAfterFire) {
            System.out.println(employee.getMonthSalary() + " company1  low");
        }


        System.out.println("\n\n\n -------------------- вторая компания\n");


        /**
         * Вторая компания
         */

        Map<Class, Double> salarsTwo = new HashMap<>();

        salarsTwo.put(Manager.class, 150500.00);
        salarsTwo.put(TopManager.class, 170500.0);
        salarsTwo.put(Operator.class, 110500.00);

        Company company2 = new Company(11000000, salarsTwo);


        /**
         * Найм сотрудников во вторую компанию
         */

        for (int i = 0; i < 60; i++) {
            company2.hire(new Operator());

            if (i >= 55) {
                company2.hire(new TopManager());
            }

            if (i >= 45) {
                company2.hire(new Manager());
            }
        }


        List<Employee> lowStaff2 = company2.getLowestSalaryStaff(5);
        List<Employee> topStaff2 = company2.getTopSalaryStaff(5);

        for (Employee employee : topStaff2) {
            System.out.println(employee.getMonthSalary() + " company2   top");
        }

        System.out.println();

        for (Employee employee : lowStaff2) {
            System.out.println(employee.getMonthSalary() + " company2  low");
        }

        System.out.println();

        List<Employee> fireList2 = company2.getEmployeeListMain();
        System.out.println(fireList2.size() + " - количество сотрудников во второй компании\n");

        for (int i = 0; i < 30; i = i + 2) {   // увольнение каждого второго сотрудника из 2 компании
            company2.fire(fireList2.get(i));

        }

        System.out.println(fireList2.size() + " - количество сотрудников во 2 компании после увольнения\n");

        List<Employee> lowStaffAfterFire2 = company2.getLowestSalaryStaff(5);
        for (Employee employee : lowStaffAfterFire2) {
            System.out.println(employee.getMonthSalary() + " company2  low");
        }


        /**
         * Проверка добавления списка сотрудников в компанию
         */
        List<Employee> hireListNew = new ArrayList<>();

        for (int i = 0; i < 50; i++) {

            hireListNew.add(new Manager());


            if (i >= 45) {
                hireListNew.add(new Operator());
                hireListNew.add(new TopManager());
            }

        }

        company2.hireAll(hireListNew);

        List<Employee> fireList3 = company2.getEmployeeListMain();
        System.out.println(fireList3.size() + " - количество сотрудников во 2 компании после найма списка сотрудников\n");


        List<Employee> lowStaff4 = company2.getLowestSalaryStaff(15);
        List<Employee> topStaff4 = company2.getTopSalaryStaff(15);

        for (Employee employee : topStaff4) {
            System.out.println(employee.getMonthSalary() + " company2   top");
        }

        System.out.println();

        for (Employee employee : lowStaff4) {
            System.out.println(employee.getMonthSalary() + " company2  low");
        }

        System.out.println();



        /**
         * Проверка запроса фиксированных зарплат сотрудников в разных компаниях
         */

        System.out.println("\nФиксированная зарплата менеджера - 1 - " + company1.getSalarys().get(Manager.class));
        System.out.println("Фиксированная зарплата топ менеджера - 1 - " + company1.getSalarys().get(TopManager.class));
        System.out.println("Фиксированная зарплата оператора - 1 - " + company1.getSalarys().get(Operator.class));


        System.out.println("\nФиксированная зарплата менеджера - 2 - " + company2.getSalarys().get(Manager.class));
        System.out.println("Фиксированная зарплата топ менеджера - 2 - " + company2.getSalarys().get(TopManager.class));
        System.out.println("Фиксированная зарплата оператора - 2 -  " + company2.getSalarys().get(Operator.class));


    }

}
