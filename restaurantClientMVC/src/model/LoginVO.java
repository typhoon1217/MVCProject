package model;

public class LoginVO {
    private String id;
    private String password;
    private String loginAs;
    private String department;

    public LoginVO() {
        super();
    }

    public LoginVO(String id, String password, String loginAs, String department) {
        this.id = id;
        this.password = password;
        this.loginAs = loginAs;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginAs() {
        return loginAs;
    }

    public void setLoginAs(String loginAs) {
        this.loginAs = loginAs;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
