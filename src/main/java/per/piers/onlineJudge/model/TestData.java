package per.piers.onlineJudge.model;

import java.util.ArrayList;

public class TestData {

    private Integer qid;
    private ArrayList<InputOutput> inputOutputs;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public ArrayList<InputOutput> getInputOutputs() {
        return inputOutputs;
    }

    public void setInputOutputs(ArrayList<InputOutput> inputOutputs) {
        this.inputOutputs = inputOutputs;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "qid=" + qid +
                ", inputOutputs=" + inputOutputs +
                '}';
    }

}
