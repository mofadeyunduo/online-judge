package per.piers.onlineJudge.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TestInfo {

    private int uid;
    private int qid;
    private Timestamp submitTime;
    private String code;
    private Double correctRate;
    private ArrayList<InputOutput> inputOutputs;

    public TestInfo() {
    }

    public TestInfo(int uid, int qid, Timestamp submitTime, String code, Double correctRate) {
        this.uid = uid;
        this.qid = qid;
        this.submitTime = submitTime;
        this.code = code;
        this.correctRate = correctRate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(Double correctRate) {
        this.correctRate = correctRate;
    }

    public ArrayList<InputOutput> getInputOutputs() {
        return inputOutputs;
    }

    public void setInputOutputs(ArrayList<InputOutput> inputOutputs) {
        this.inputOutputs = inputOutputs;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "sid=" + uid +
                ", qid=" + qid +
                ", submitTime=" + submitTime +
                ", code='" + code + '\'' +
                ", correctRate=" + correctRate +
                '}';
    }

}