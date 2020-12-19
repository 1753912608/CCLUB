package cclub.demo.dao;

public class chat {
    private String interview_id;
    private String type;
    private String chat_content;

    public chat(String interview_id, String type, String chat_content) {
        this.interview_id = interview_id;
        this.type = type;
        this.chat_content = chat_content;
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
}
