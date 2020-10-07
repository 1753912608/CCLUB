package cclub.demo.dao;

import java.util.Date;

public class Interview {
    private String interview_id;
    private String interview_create_user_id;
    private Date interview_begin_time;
    private String interview_company_name;
    private String interview_candidate_position;
    private String interview_candidate_phone;
    private String interview_candidate_name;
    private String interview_candidate_mail;
    private String interview_judge_phone;
    private String interview_judge_name;
    private String interview_judge_mail;
    private int interview_recording;
    private int interview_candidate_resume;
    private String interview_invitation_judge_url;
    private String interview_invitation_judge_notice;
    private String interview_invitation_candidate_url;
    private String interview_invitation_candidate_notice;
    private String interview_room_state;

    public Interview(String interview_id, String interview_create_user_id,
                     Date interview_begin_time, String interview_company_name,
                     String interview_candidate_position, String interview_candidate_phone,
                     String interview_candidate_name, String interview_candidate_mail,
                     String interview_judge_phone, String interview_judge_name,
                     String interview_judge_mail, int interview_recording,
                     int interview_candidate_resume, String interview_invitation_judge_url,
                     String interview_invitation_judge_notice, String interview_invitation_candidate_url,
                     String interview_invitation_candidate_notice, String interview_room_state) {
        this.interview_id = interview_id;
        this.interview_create_user_id = interview_create_user_id;
        this.interview_begin_time = interview_begin_time;
        this.interview_company_name = interview_company_name;
        this.interview_candidate_position = interview_candidate_position;
        this.interview_candidate_phone = interview_candidate_phone;
        this.interview_candidate_name = interview_candidate_name;
        this.interview_candidate_mail = interview_candidate_mail;
        this.interview_judge_phone = interview_judge_phone;
        this.interview_judge_name = interview_judge_name;
        this.interview_judge_mail = interview_judge_mail;
        this.interview_recording = interview_recording;
        this.interview_candidate_resume = interview_candidate_resume;
        this.interview_invitation_judge_url = interview_invitation_judge_url;
        this.interview_invitation_judge_notice = interview_invitation_judge_notice;
        this.interview_invitation_candidate_url = interview_invitation_candidate_url;
        this.interview_invitation_candidate_notice = interview_invitation_candidate_notice;
        this.interview_room_state = interview_room_state;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public void setInterview_create_user_id(String interview_create_user_id) {
        this.interview_create_user_id = interview_create_user_id;
    }

    public void setInterview_begin_time(Date interview_begin_time) {
        this.interview_begin_time = interview_begin_time;
    }

    public void setInterview_company_name(String interview_company_name) {
        this.interview_company_name = interview_company_name;
    }

    public void setInterview_candidate_position(String interview_candidate_position) {
        this.interview_candidate_position = interview_candidate_position;
    }

    public void setInterview_candidate_phone(String interview_candidate_phone) {
        this.interview_candidate_phone = interview_candidate_phone;
    }

    public void setInterview_candidate_name(String interview_candidate_name) {
        this.interview_candidate_name = interview_candidate_name;
    }

    public void setInterview_candidate_mail(String interview_candidate_mail) {
        this.interview_candidate_mail = interview_candidate_mail;
    }

    public void setInterview_judge_phone(String interview_judge_phone) {
        this.interview_judge_phone = interview_judge_phone;
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

    public void setInterview_invitation_judge_url(String interview_invitation_judge_url) {
        this.interview_invitation_judge_url = interview_invitation_judge_url;
    }

    public void setInterview_invitation_judge_notice(String interview_invitation_judge_notice) {
        this.interview_invitation_judge_notice = interview_invitation_judge_notice;
    }

    public void setInterview_invitation_candidate_url(String interview_invitation_candidate_url) {
        this.interview_invitation_candidate_url = interview_invitation_candidate_url;
    }

    public void setInterview_invitation_candidate_notice(String interview_invitation_candidate_notice) {
        this.interview_invitation_candidate_notice = interview_invitation_candidate_notice;
    }

    public void setInterview_room_state(String interview_room_state) {
        this.interview_room_state = interview_room_state;
    }

    public String getInterview_id() {
        return interview_id;
    }

    public String getInterview_create_user_id() {
        return interview_create_user_id;
    }

    public Date getInterview_begin_time() {
        return interview_begin_time;
    }

    public String getInterview_company_name() {
        return interview_company_name;
    }

    public String getInterview_candidate_position() {
        return interview_candidate_position;
    }

    public String getInterview_candidate_phone() {
        return interview_candidate_phone;
    }

    public String getInterview_candidate_name() {
        return interview_candidate_name;
    }

    public String getInterview_candidate_mail() {
        return interview_candidate_mail;
    }

    public String getInterview_judge_phone() {
        return interview_judge_phone;
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

    public String getInterview_invitation_judge_url() {
        return interview_invitation_judge_url;
    }

    public String getInterview_invitation_judge_notice() {
        return interview_invitation_judge_notice;
    }

    public String getInterview_invitation_candidate_url() {
        return interview_invitation_candidate_url;
    }

    public String getInterview_invitation_candidate_notice() {
        return interview_invitation_candidate_notice;
    }

    public String getInterview_room_state() {
        return interview_room_state;
    }
}
