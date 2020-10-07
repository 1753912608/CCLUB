package cclub.demo.dao;

public class resume {
    private String resume_interview_id;
    private String resume_content_url;

    public resume(String resume_interview_id, String resume_content_url) {
        this.resume_interview_id = resume_interview_id;
        this.resume_content_url = resume_content_url;
    }

    public void setResume_interview_id(String resume_interview_id) {
        this.resume_interview_id = resume_interview_id;
    }

    public void setResume_content_url(String resume_content_url) {
        this.resume_content_url = resume_content_url;
    }

    public String getResume_interview_id() {
        return resume_interview_id;
    }

    public String getResume_content_url() {
        return resume_content_url;
    }
}
