package week1.task10;

import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.LocalDate;
import java.util.List;
@FunctionalInterface
interface ReportGenerator<T, R> {
    R generate(List<T> data);
}


class Student {
    int studentId;
    String name;
    LocalDate admissionDate;
    public Student(int studentId, String name, LocalDate admissionDate) {
        this.studentId = studentId;
        this.name = name;
        this.admissionDate = admissionDate;
    }
    public int getStudentId() {return studentId;}
    public void setStudentId(int studentId) {this.studentId = studentId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public LocalDate getAdmissionDate() {return admissionDate;}
    public void setAdmissionDate(LocalDate admissionDate) {this.admissionDate = admissionDate;}
}


class Payment {
    int paymentId;
    int studentId;
    double amount;
    boolean isPaid;
    public Payment(int paymentId, int studentId, double amount, boolean isPaid) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.isPaid = isPaid;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}

class Enrollment {
    int enrollmentId;
    int studentId;
    int courseId;
    public Enrollment(int enrollmentId, int studentId, int courseId) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
    }
    public int getEnrollmentId() {
        return enrollmentId;
    }
    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

class Course {
    int courseId;
    String courseName;
    String trainerName;
    double fee;
    public Course(int courseId, String courseName, String trainerName, double fee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.trainerName = trainerName;
        this.fee = fee;
    }
    public int getCourseId() {return courseId;}
    public void setCourseId(int courseId) {this.courseId = courseId;}
    public String getCourseName() {return courseName;}
    public void setCourseName(String courseName) {this.courseName = courseName;}
    public String getTrainerName() {return trainerName;}
    public void setTrainerName(String trainerName) {this.trainerName = trainerName;}
    public double getFee() {return fee;}
    public void setFee(double fee) {this.fee = fee;}

}


public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "Alice", LocalDate.of(2026, Month.JANUARY, 10)),
                new Student(2, "Bob", LocalDate.of(2026, Month.FEBRUARY, 5)),
                new Student(3, "Charlie", LocalDate.of(2026, Month.JANUARY, 20)),
                new Student(4, "David", LocalDate.of(2026, Month.MARCH, 15))
        );

        List<Course> courses = Arrays.asList(
                new Course(101, "Java Fullstack", "Trainer A", 25000),
                new Course(102, "Python Fullstack", "Trainer B", 20000),
                new Course(103, "DevOps", "Trainer C", 18000),
                new Course(104, "Data Science", "Trainer D", 30000)
        );

        List<Enrollment> enrollments = Arrays.asList(
                new Enrollment(1, 1, 101),
                new Enrollment(2, 2, 102),
                new Enrollment(3, 3, 103),
                new Enrollment(4, 4, 104),
                new Enrollment(5, 1, 104),
                new Enrollment(6, 2, 101)
        );

        List<Payment> payments = Arrays.asList(
                new Payment(1, 1, 25000, true),
                new Payment(2, 2, 20000, false),
                new Payment(3, 3, 18000, true),
                new Payment(4, 4, 30000, false)
        );

        ReportGenerator<Enrollment, Map<String, Long>> courseWiseEnrollment = (data) ->
                data.stream()
                        .collect(Collectors.groupingBy(
                                e -> courses.stream()
                                        .filter(c -> c.courseId == e.courseId)
                                        .findFirst().get().courseName,
                                Collectors.counting()
                        ));

        ReportGenerator<Payment, Double> totalRevenue = (data) ->
                data.stream()
                        .filter(p -> p.isPaid)
                        .mapToDouble(p -> p.amount)
                        .sum();

        ReportGenerator<Payment, Double> pendingPayments = (data) ->
                data.stream()
                        .filter(p -> !p.isPaid)
                        .mapToDouble(p -> p.amount)
                        .sum();

        ReportGenerator<Course, Map<String, List<String>>> trainerWiseCourse = (data) ->
                data.stream()
                        .collect(Collectors.groupingBy(
                                c -> c.trainerName,
                                Collectors.mapping(c -> c.courseName, Collectors.toList())
                        ));

        ReportGenerator<Student, Map<Month, Long>> monthlyAdmission = (data) ->
                data.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.admissionDate.getMonth(),
                                Collectors.counting()
                        ));

        ReportGenerator<Course, List<Course>> topHighFeeCourses = (data) ->
                data.stream()
                        .sorted(Comparator.comparingDouble(Course::getFee).reversed())
                        .limit(5)
                        .collect(Collectors.toList());

        System.out.println("Course Wise Enrollment Report:");
        courseWiseEnrollment.generate(enrollments)
                .forEach((course, count) -> System.out.println(course + " : " + count + " Students"));

        System.out.println("\nTotal Revenue: ₹" + totalRevenue.generate(payments));
        System.out.println("Pending Payments: ₹" + pendingPayments.generate(payments));

        System.out.println("\nTrainer Wise Course Report:");
        trainerWiseCourse.generate(courses)
                .forEach((trainer, courseList) -> System.out.println(trainer + " : " + courseList));

        System.out.println("\nMonthly Admission Report:");
        monthlyAdmission.generate(students)
                .forEach((month, count) -> System.out.println(month + " : " + count + " Admissions"));

        System.out.println("\nTop 5 High-Fee Courses:");
        topHighFeeCourses.generate(courses)
                .forEach(c -> System.out.println(c.courseName + " - ₹" + c.fee));
    }
}
