package cclub.demo.dao;

public class Interview {
    private String interview_id;
    private String interview_create_user_id;
    private String interview_begin_time;
    private String interview_company_name;
    private String interview_candidate_position;
    private String interview_candidate_name;
    private String interview_candidate_mail;
    private String interview_judge_name;
    private String interview_judge_mail;
    private int interview_recording;
    private int interview_candidate_resume;

    public Interview(String interview_id, String interview_create_user_id, String interview_begin_time, String interview_company_name, String interview_candidate_position, String interview_candidate_name, String interview_candidate_mail, String interview_judge_name, String interview_judge_mail, int interview_recording, int interview_candidate_resume) {
        this.interview_id = interview_id;
        this.interview_create_user_id = interview_create_user_id;
        this.interview_begin_time = interview_begin_time;
        this.interview_company_name = interview_company_name;
        this.interview_candidate_position = interview_candidate_position;
        this.interview_candidate_name = interview_candidate_name;
        this.interview_candidate_mail = interview_candidate_mail;
        this.interview_judge_name = interview_judge_name;
        this.interview_judge_mail = interview_judge_mail;
        this.interview_recording = interview_recording;
        this.interview_candidate_resume = interview_candidate_resume;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public void setInterview_create_user_id(String interview_create_user_id) {
        this.interview_create_user_id = interview_create_user_id;
    }

    public void setInterview_begin_time(String interview_begin_time) {
        this.interview_begin_time = interview_begin_time;
    }

    public void setInterview_company_name(String interview_company_name) {
        this.interview_company_name = interview_company_name;
    }

    public void setInterview_candidate_position(String interview_candidate_position) {
        this.interview_candidate_position = interview_candidate_position;
    }

    public void setInterview_candidate_name(String interview_candidate_name) {
        this.interview_candidate_name = interview_candidate_name;
    }

    public void setInterview_candidate_mail(String interview_candidate_mail) {
        this.interview_candidate_mail = interview_candidate_mail;
    }

    public void setInterview_judge_name(String interview_judge_name) {
        this.interview_judge_name = interview_judge_name;
    }

    public void setInterview_judge_mail(String interview_judge_mail) {
        this.interview_judge_mail = interview_judge_mail;
    }

    public void setInterview_recording(int interview_recording) {
        this.interview_recording = interview_recording;
    }

    public void setInterview_candidate_resume(int interview_candidate_resume) {
        this.interview_candidate_resume = interview_candidate_resume;
    }

    public String getInterview_id() {
        return interview_id;
    }

    public String getInterview_create_user_id() {
        return interview_create_user_id;
    }

    public String getInterview_begin_time() {
        return interview_begin_time;
    }

    public String getInterview_company_name() {
        return interview_company_name;
    }

    public String getInterview_candidate_position() {
        return interview_candidate_position;
    }

    public String getInterview_candidate_name() {
        return interview_candidate_name;
    }

    public String getInterview_candidate_mail() {
        return interview_candidate_mail;
    }

    public String getInterview_judge_name() {
        return interview_judge_name;
    }

    public String getInterview_judge_mail() {
        return interview_judge_mail;
    }

    public int getInterview_recording() {
        return interview_recording;
    }

    public int getInterview_candidate_resume() {
        return interview_candidate_resume;
    }
}
