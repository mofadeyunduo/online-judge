package per.piers.onlineJudge.model;

public class Advisor {

    private Integer id;
    private Integer uidUser;
    private Integer uidAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUidUser() {
        return uidUser;
    }

    public void setUidUser(Integer uidUser) {
        this.uidUser = uidUser;
    }

    public Integer getUidAdmin() {
        return uidAdmin;
    }

    public void setUidAdmin(Integer uidAdmin) {
        this.uidAdmin = uidAdmin;
    }

    @Override
    public String toString() {
        return "Advisor{" +
                "id=" + id +
                ", uidUser=" + uidUser +
                ", uidAdmin=" + uidAdmin +
                '}';
    }

}
