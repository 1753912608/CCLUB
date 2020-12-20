package cclub.demo.dao;

public class chat {
    private String interview_id;
    private String type;
    private String chat_content;
    private String from_user;

    public chat(String interview_id, String type, String chat_content, String from_user) {
        this.interview_id = interview_id;
        this.type = type;
        this.chat_content = chat_content;
        this.from_user = from_user;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public String getInterview_id() {
        return interview_id;
    }

    public String getChat_content() {
        return chat_content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getFrom_user() {
        return from_user;
    }

    @Override
    public String toString() {
        return "chat{" +
                "interview_id='" + interview_id + '\'' +
                ", type='" + type + '\'' +
                ", chat_content='" + chat_content + '\'' +
                ", from_user='" + from_user + '\'' +
                '}';
    }
}
