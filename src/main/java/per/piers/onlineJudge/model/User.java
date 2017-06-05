package per.piers.onlineJudge.model;

public class User {

    // TODO: Java Bean Validation
    private Integer id;
    private String email;
    private String password;
    private String name;
    private Sex sex;
    private Boolean enabled;
    private String role;

    public User() {
    }

    public User(Integer id, String email, String password, String name, Sex sex, Boolean enabled, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.enabled = enabled;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                '}';
    }

}

