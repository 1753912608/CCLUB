package cclub.demo.dao;

public class User {
    private String user_id;
    private String user_campany;

    public User(String user_id, String user_campany) {
        this.user_id = user_id;
        this.user_campany = user_campany;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_campany(String user_campany) {
        this.user_campany = user_campany;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_campany() {
        return user_campany;
    }
}
