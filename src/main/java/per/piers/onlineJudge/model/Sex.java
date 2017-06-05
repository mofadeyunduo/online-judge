package per.piers.onlineJudge.model;

public enum Sex {

    MALE(0), FEMALE(1);

    private int id;

    Sex(int id) {
        this.id = id;
    }

    public static Sex getSexType(int i) {
        if (i == 0) return Sex.MALE;
        else return Sex.FEMALE;
    }

    public int getId() {
        return id;
    }

}
