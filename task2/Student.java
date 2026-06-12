package week1.task2;

import java.util.List;

public class Student {
    private Integer studentId;
    private String studentName;
    private String qualification;
    private List<String> skills;
    private Integer experience;
    private String preferredTechnology;
    private Double budget;

    public Student(Integer studentId, String studentName, String qualification, List<String> skills, Integer experience, String preferredTechnology, Double budget) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.qualification = qualification;
        this.skills = skills;
        this.experience = experience;
        this.preferredTechnology = preferredTechnology;
        this.budget = budget;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getQualification() {
        return qualification;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getPreferredTechnology() {
        return preferredTechnology;
    }

    public Double getBudget() {
        return budget;
    }

}
