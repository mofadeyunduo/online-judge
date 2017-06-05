package per.piers.onlineJudge.model;

public class AllScore {

    private Integer id;
    private User user;
    private Category category;
    private Double practiceScore;
    private Double usualScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPracticeScore() {
        return practiceScore;
    }

    public void setPracticeScore(Double practiceScore) {
        this.practiceScore = practiceScore;
    }

    public Double getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(Double usualScore) {
        this.usualScore = usualScore;
    }

    @Override
    public String toString() {
        return "AllScore{" +
                "id=" + id +
                ", user=" + user +
                ", category=" + category +
                ", practiceScore=" + practiceScore +
                ", usualScore=" + usualScore +
                '}';
    }
}
