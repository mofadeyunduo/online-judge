package per.piers.onlineJudge.model;

public class Score {

    private int uid;
    private double sumCorrectRate;
    private int rank;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getSumCorrectRate() {
        return sumCorrectRate;
    }

    public void setSumCorrectRate(double sumCorrectRate) {
        this.sumCorrectRate = sumCorrectRate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
