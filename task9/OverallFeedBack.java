package week1.task9;

public class OverallFeedBack {
    private String name;
    private Double overalRating;
    private String performance;
    private String isEligible;
    private String suggestion;

    public OverallFeedBack() {

    }

    public OverallFeedBack(String name, Double overalRating, String performance, String isEligible, String suggestion) {
        this.name = name;
        this.overalRating = overalRating;
        this.performance = performance;
        this.isEligible = isEligible;
        this.suggestion = suggestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOveralRating() {
        return overalRating;
    }

    public void setOveralRating(Double overalRating) {
        this.overalRating = overalRating;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(String isEligible) {
        this.isEligible = isEligible;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "OverallFeedBack{" +
                "name='" + name + '\'' +
                ", overalRating=" + overalRating +
                ", performance='" + performance + '\'' +
                ", isEligible='" + isEligible + '\'' +
                ", suggestion='" + suggestion + '\'' +
                '}';
    }


}
