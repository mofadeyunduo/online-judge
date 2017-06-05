package per.piers.onlineJudge.model;

import java.util.List;

public class Question {

    private Integer id;
    private Integer qid;
    private String name;
    private String description;
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", qid=" + qid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

}
