package week1.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "Aisha Khan", "Java Fullstack", 2024, 88.5, 0, 4.8,
                        Arrays.asList("Java", "Spring Boot", "SQL"), true, true),
                new Student(2, "Vikram Singh", "FrontEnd", 2024, 58.0, 2, 3.2,
                        Arrays.asList("HTML", "CSS"), true, false),
                new Student(3, "Rohan Sharma", "Python Fullstack", 2023, 75.0, 0, 4.1,
                        Arrays.asList("Python", "Django"), false, true),
                new Student(4, "Sneha Patel", "Java Fullstack", 2025, 82.0, 0, 4.5,
                        Arrays.asList("Java", "MySQL", "Microservices"), true, true),
                new Student(5, "Karan Verma", "FrontEnd", 2023, 65.5, 1, 3.8,
                        Arrays.asList("JavaScript", "React"), true, true)
        );

        List<Student> eligibleStudents = students.stream()
                .filter(s -> s.getGraduationYear() >= 2022 &&
                        s.getPercentage() >= 60 &&
                        s.getBacklogs() == 0 &&
                        s.getMockRating() >= 4 &&
                        s.getResumeAvailable() &&
                        s.getFeePaid() &&
                        (s.getSkills().contains("Java") || s.getSkills().contains("Python"))
                )
                .toList();
        Function<Student,String> getRejectedStudents=s->{
            List<String>reasons=new ArrayList<>();
            if(s.getGraduationYear()<2022) reasons.add("Graduation Year < 2022");
            if(!(s.getPercentage()<60)) reasons.add("Percentage < 60%");
            if(!(s.getBacklogs()>0)) reasons.add("Backlogs Pending");
            if(!(s.getMockRating()<4)) reasons.add("Mockrating <4");
            if(!s.getResumeAvailable()) reasons.add("Resume not available");
            if(!s.getFeePaid()) reasons.add("fee not Paid");
            if(!(s.getSkills().contains("Java") || s.getSkills().contains("Python") )) reasons.add("Java or Python not in Skills");
            return String.join(",",reasons);
        };
        System.out.println("Eligible Students for Placement: ");
        eligibleStudents.forEach(s -> System.out.println(s.toString()));
        System.out.println("Rejected Students: ");
        students.forEach(s->{
            String reason=getRejectedStudents.apply(s);
            System.out.println(s.getStudentName()+" - Reason: "+reason);
        });
    }
}
