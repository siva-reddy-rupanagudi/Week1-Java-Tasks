package week1.task1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    static Integer per = 0;

    public static void main(String[] args) {

        Employee emp = new Employee(
                101, "Ravi", "IT", "Developer", 4, 60000.00, 5.0
        );

        Predicate<Employee> isHighExperience = emp1 -> emp1.getExperience() >= 5;
        Predicate<Employee> isHighRated = emp1 -> emp1.getPerformanceRating() >= 4.5;
        Predicate<Employee> isLowrated = emp1 -> emp1.getExperience() < 3;
        Predicate<Employee> isDeveloper = emp1 -> emp1.getRole().equalsIgnoreCase("developer");
        Predicate<Employee> isTester = emp1 -> emp1.getRole().equalsIgnoreCase("tester");

        Function<Employee, Integer> getHikedPercentage = (emp1) -> {
            int hike = 0;
            if (isLowrated.test(emp1)) return hike;
            if (isHighRated.test(emp1)) hike += 20;
            if (isHighExperience.test(emp1)) hike += 15;
            if (isDeveloper.test(emp1)) hike += 10;
            if (isTester.test(emp1)) hike += 8;
            return hike;
        };

        Function<Employee, Double> getUpdatedSalary = emp1 -> {
            Integer hike = getHikedPercentage.apply(emp1);
            return emp1.getSalary() + (hike / 100.0) * emp1.getSalary();
        };

        SalaryProcessor salp = (employee) -> {
            Double apply = getUpdatedSalary.apply(employee);
            return apply;
        };

        Consumer<Employee> con = (emp1) -> {
            int hike = getHikedPercentage.apply(emp1);
            System.out.println("week1.task1.Employee : " + emp1.getEmployeeName());
            System.out.println("Role : " + emp1.getRole());
            System.out.println("Old Salary : " + emp1.getSalary());
            double finalSalary = salp.process(emp1);
            System.out.println("Hike Applied : " + hike + "%");
            System.out.println("Final Salary : " + finalSalary);
        };


        con.accept(emp);
    }
}