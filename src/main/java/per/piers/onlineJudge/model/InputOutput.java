package per.piers.onlineJudge.model;

/**
 * Created by Piers on 2017/5/15.
 */
public class InputOutput {

    private Integer id;
    private String input;
    private String output;
    private Boolean isCorrect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "InputOutput{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

}
