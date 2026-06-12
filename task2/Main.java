package week1.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.stream;

public class Main {
    public static void main(String[] args) {
        List<Courses> courseCatalog = Arrays.asList(
                new Courses(1, "Java Fullstack Masterclass", "Java", "Advanced", 25000.0, "6 Months", 4.7),
                new Courses(2, "Spring Boot Microservices", "Java", "Advanced", 18000.0, "3 Months", 4.8),
                new Courses(3, "Core Java for Beginners", "Java", "Beginner", 12000.0, "2 Months", 4.5),
                new Courses(4, "Python Fullstack Bootcamp", "Python", "Advanced", 22000.0, "5 Months", 4.6),
                new Courses(5, "Data Science with Python", "Python", "Advanced", 28000.0, "6 Months", 4.9),
                new Courses(6, "Python Programming Basics", "Python", "Beginner", 9000.0, "1 Month", 4.3),
                new Courses(7, "Advanced Java Performance", "Java", "Advanced", 15000.0, "2 Months", 4.6)
        );
        Student suresh = new Student(
                101, "Suresh", "B.Tech", Arrays.asList("Core Java", "SQL"), 3, "Java", 30000.0
        );

        List<Courses> courses = courseCatalog.
                stream()
                .filter(course -> course.getFee() <= suresh.getBudget())
                .filter(course -> suresh.getPreferredTechnology().equalsIgnoreCase(course.getTechnology()))
                .filter(course ->{
                   if(suresh.getExperience()==0) return course.getLevel().equalsIgnoreCase("Beginner");
                   else if(suresh.getExperience()>=3) return course.getLevel().equalsIgnoreCase("Advanced");
                   return course.getLevel().equalsIgnoreCase("Intermediate");
                }).sorted().toList();
        Consumer<Courses> con=(cou)->System.out.println(cou.toString());
        System.out.println("Recommended Courses for "+suresh.getStudentName()+":");
        courses.forEach(con);
    }
}
