package cclub.demo.dao;

public class User {
    private String user_id;
    private String user_company;

    public User(String user_id, String user_company) {
        this.user_id = user_id;
        this.user_company = user_company;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_company(String user_company) {
        this.user_company = user_company;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_company() {
        return user_company;
    }
}
