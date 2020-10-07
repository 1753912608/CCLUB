package cclub.demo.dao;

public class remarks {
    private String remarks_interview_id;
    private String remarks_state;
    private String remarks_content;

    public remarks(String remarks_interview_id, String remarks_state, String remarks_content) {
        this.remarks_interview_id = remarks_interview_id;
        this.remarks_state = remarks_state;
        this.remarks_content = remarks_content;
    }

    public void setRemarks_interview_id(String remarks_interview_id) {
        this.remarks_interview_id = remarks_interview_id;
    }

    public void setRemarks_state(String remarks_state) {
        this.remarks_state = remarks_state;
    }

    public void setRemarks_content(String remarks_content) {
        this.remarks_content = remarks_content;
    }

    public String getRemarks_interview_id() {
        return remarks_interview_id;
    }

    public String getRemarks_state() {
        return remarks_state;
    }

    public String getRemarks_content() {
        return remarks_content;
    }
}
