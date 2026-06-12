package week1.task1;

public class Employee {
    private Integer employeeId;
    private String employeeName;
    private String department;
    private String role;
    private Integer experience;
    private Double salary;
    private Double performanceRating;


    public Employee(Integer employeeId, String employeeName, String department, String role, Integer experience, Double salary, Double performanceRating) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.role = role;
        this.experience = experience;
        this.salary = salary;
        this.performanceRating = performanceRating;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public Integer getExperience() {
        return experience;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getPerformanceRating() {
        return performanceRating;
    }


}