package week1.task2;

public class Courses implements Comparable<Courses>{
    private Integer courseId;
    private String courseName;
    private String technology;
    private String level;
    private Double fee;
    private String duration;
    private Double rating;

    public Courses(Integer courseId, String courseName, String technology, String level, Double fee, String duration, Double rating) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.technology = technology;
        this.level = level;
        this.fee = fee;
        this.duration = duration;
        this.rating = rating;
    }


    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTechnology() {
        return technology;
    }

    public String getLevel() {
        return level;
    }

    public Double getFee() {
        return fee;
    }

    public String getDuration() {
        return duration;
    }

    public Double getRating() {
        return rating;
    }



    @Override
    public int compareTo(Courses o) {
        return Double.compare(o.rating,this.rating);
    }

    @Override
    public String toString() {
        return courseName + " - ₹" + fee + " - Rating: " + rating;
    }
}
