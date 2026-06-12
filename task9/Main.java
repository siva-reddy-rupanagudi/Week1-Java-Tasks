package week1.task9;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<OverallFeedBack> overallFeedBackList = new ArrayList<>();

        List<InterviewFeedback> feedbackList = Arrays.asList(
                new InterviewFeedback(
                        101,
                        "Rahul Sharma",
                        8.5, 7.8, 8.2, 7.5, 8.0,
                        Arrays.asList("Explain polymorphism in Java", "Solve a problem using binary search"),
                        Arrays.asList("Strong problem-solving skills", "Good communication"),
                        Arrays.asList("Needs improvement in time management", "Occasional nervousness under pressure")
                ),
                new InterviewFeedback(
                        102,
                        "Anita Verma",
                        7.2, 8.4, 7.0, 8.8, 7.5,
                        Arrays.asList("What is multithreading?", "Write SQL query for joins"),
                        Arrays.asList("Excellent confidence", "Clear communication"),
                        Arrays.asList("Average coding speed", "Limited exposure to advanced algorithms")
                ),
                new InterviewFeedback(
                        103,
                        "Suresh Kumar",
                        9.0, 6.5, 5.0, 7.0, 9.1,
                        Arrays.asList("Implement quicksort", "Explain design patterns"),
                        Arrays.asList("Strong technical depth", "Great coding ability"),
                        Arrays.asList("Needs to improve communication", "Low confidence in presentations")
                ),
                new InterviewFeedback(
                        104,
                        "Priya Nair",
                        7.8, 8.9, 7.5, 8.2, 7.7,
                        Arrays.asList("Explain REST API", "Solve linked list reversal"),
                        Arrays.asList("Good communication", "Confident speaker"),
                        Arrays.asList("Average coding skills", "Struggles with complex problem-solving")
                ),
                new InterviewFeedback(
                        105,
                        "Arjun Mehta",
                        8.0, 7.2, 5.0, 7.9, 8.3,
                        Arrays.asList("Explain garbage collection in Java", "Write program for Fibonacci series"),
                        Arrays.asList("Solid coding foundation", "Good logical thinking"),
                        Arrays.asList("Needs to improve confidence", "Sometimes unclear communication")
                )
        );

        Function<InterviewFeedback, Double> getOveralRating = inerFeed -> {
            return (inerFeed.getTechnicalRating() + inerFeed.getCodingRating() +
                    inerFeed.getCommunicationRating() + inerFeed.getConfidenceRating() +
                    inerFeed.getProblemSolvingRating()) / 5;
        };

        Function<Double, String> perforStatus = rate -> {
            if (rate >= 8) return "Excellent";
            else if (rate >= 6) return "Good";
            else if (rate >= 4) return "Average";
            return "Needs Improvement";
        };

        Predicate<InterviewFeedback> isElegible = interviewFeedback ->
                getOveralRating.apply(interviewFeedback) >= 6 &&
                        interviewFeedback.getCodingRating() >= 6;


        Function<InterviewFeedback, String> getSugesstions = interviewFeedback -> {
            List<String> improvementAreas = new ArrayList<>();

            if (perforStatus.apply(interviewFeedback.getCodingRating()).equals("Needs Improvement") ||
                    perforStatus.apply(interviewFeedback.getCodingRating()).equals("Average")) {
                improvementAreas.add("coding practice");
            }
            if (perforStatus.apply(interviewFeedback.getCommunicationRating()).equals("Needs Improvement") ||
                    perforStatus.apply(interviewFeedback.getCommunicationRating()).equals("Average")) {
                improvementAreas.add("communication skills");
            }
            if (perforStatus.apply(interviewFeedback.getConfidenceRating()).equals("Needs Improvement") ||
                    perforStatus.apply(interviewFeedback.getConfidenceRating()).equals("Average")) {
                improvementAreas.add("confidence building");
            }
            if (perforStatus.apply(interviewFeedback.getProblemSolvingRating()).equals("Needs Improvement") ||
                    perforStatus.apply(interviewFeedback.getProblemSolvingRating()).equals("Average")) {
                improvementAreas.add("problem-solving");
            }
            if (perforStatus.apply(interviewFeedback.getTechnicalRating()).equals("Needs Improvement") ||
                    perforStatus.apply(interviewFeedback.getTechnicalRating()).equals("Average")) {
                improvementAreas.add("technical concepts");
            }

            if (improvementAreas.isEmpty()) {
                return "Great job! You are strong in all areas.";
            } else {
                return "Suggestions: Improve " + String.join(", ", improvementAreas) + ".";
            }
        };

        feedbackList.stream()
                .map(st -> {
                    OverallFeedBack ov = new OverallFeedBack(
                            st.getStudentName(),
                            getOveralRating.apply(st),
                            perforStatus.apply(getOveralRating.apply(st)),
                            isElegible.test(st) ? "YES" : "NO",
                            getSugesstions.apply(st)
                    );
                    overallFeedBackList.add(ov);
                    return st;
                }).toList();

        overallFeedBackList.forEach(ov -> System.out.println(ov.toString()));
    }
}
